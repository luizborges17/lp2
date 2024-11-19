package org.example.services;


import io.micronaut.data.model.Sort;
import io.micronaut.http.HttpResponse;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import org.example.dtos.LeilaoDto;
import org.example.dtos.LeilaoForm;
import org.example.exceptions.LeilaoSemEntidadesFinanceirasAssociadas;
import org.example.models.EntidadeFinanceira;
import org.example.models.Leilao;
import org.example.repositorys.EntidadesFinanceirasRepository;
import org.example.repositorys.LeilaoRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class LeilaoService {

	@Inject
	private LeilaoRepository leilaoRepository;

	@Inject
	private EntidadesFinanceirasRepository entidadesFinanceirasRepository;

	// GetAll
	public HttpResponse<List<LeilaoDto>> getAll() {
		List<Leilao> leiloes = leilaoRepository.findAll();
		List<LeilaoDto> leiloesDtos = new ArrayList<>();

		for (Leilao leilao : leiloes) {
			leiloesDtos.add(converteParaDto(leilao));
		}
		return HttpResponse.ok(leiloesDtos);
	}

	// GetAll OrderBy DataOcorrencia
	public HttpResponse<List<LeilaoDto>> getAllOrderByDataOcorrencia() {
		// Ordenando pela data de ocorrência de forma decrescente
		Sort sort = Sort.of(Sort.Order.desc("leiDataOcorrencia"));
		List<Leilao> leiloes = leilaoRepository.findAll(sort);

		List<LeilaoDto> leiloesDtos = new ArrayList<>();

		for (Leilao leilao : leiloes) {
			leiloesDtos.add(converteParaDto(leilao));
		}

		return HttpResponse.ok(leiloesDtos);
	}

	// Save
	public HttpResponse<LeilaoDto> save(LeilaoForm leilaoForm) {

		if (leilaoForm.getIdEntidadesFinanceiras().isEmpty()) {
			throw new LeilaoSemEntidadesFinanceirasAssociadas("Necessário informar ao menos uma entidade financeira para o leilão!!!");
		}

		List<EntidadeFinanceira> entidadeFinanceiras = new ArrayList<>();

		for (Integer idEntidadeFinanceira : leilaoForm.getIdEntidadesFinanceiras()) {
			EntidadeFinanceira entidadeFinanceira = entidadesFinanceirasRepository.findById(idEntidadeFinanceira)
					.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + idEntidadeFinanceira + " na classe: " + EntidadeFinanceira.class.toString()));
			entidadeFinanceiras.add(entidadeFinanceira);
		}

		Leilao leilao = new Leilao(
				leilaoForm.getLeiDataOcorrencia(),
				leilaoForm.getLeiDataVisitacao(),
				leilaoForm.getLeiEndereco(),
				leilaoForm.getLeiCidade(),
				leilaoForm.getLeiestado(),
				leilaoForm.getLeiEnderecoWeb(),
				entidadeFinanceiras
		);

		return HttpResponse.ok(converteParaDto(leilaoRepository.save(leilao)));
	}

	// Update
	public HttpResponse<LeilaoDto> update(LeilaoForm leilaoForm, Integer id) {

		if (leilaoForm.getIdEntidadesFinanceiras().isEmpty()) {
			throw new LeilaoSemEntidadesFinanceirasAssociadas("Necessário informar ao menos uma entidade financeira para o leilão!!!");
		}

		List<EntidadeFinanceira> entidadeFinanceiras = new ArrayList<>();

		for (Integer idEntidadeFinanceira : leilaoForm.getIdEntidadesFinanceiras()) {
			EntidadeFinanceira entidadeFinanceira = entidadesFinanceirasRepository.findById(idEntidadeFinanceira)
					.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + idEntidadeFinanceira + " na classe: " + EntidadeFinanceira.class.toString()));
			entidadeFinanceiras.add(entidadeFinanceira);
		}

		Leilao leilao = leilaoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + Leilao.class.toString()));

		leilao.setLeiCidade(leilaoForm.getLeiCidade());
		leilao.setLeiDataOcorrencia(leilaoForm.getLeiDataOcorrencia());
		leilao.setLeiDataVisitacao(leilaoForm.getLeiDataVisitacao());
		leilao.setLeiEndereco(leilaoForm.getLeiEndereco());
		leilao.setLeiEnderecoWeb(leilaoForm.getLeiEnderecoWeb());
		leilao.setLeiEstado(leilaoForm.getLeiestado());
		leilao.setEntidadesFinanceiras(entidadeFinanceiras);

		return HttpResponse.ok(converteParaDto(leilaoRepository.save(leilao)));
	}

	// Delete
	@Transactional
	public HttpResponse<Void> delete(Integer id) {
		leilaoRepository.deleteById(id);
		return HttpResponse.noContent();
	}

	// GetById
	public HttpResponse<LeilaoDto> getById(Integer id) {
		return HttpResponse.ok(converteParaDto(
				leilaoRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + Leilao.class.toString()))
		));
	}

	// Converter
	private LeilaoDto converteParaDto(Leilao leilao) {
		List<String> entidadeFinanceirasNomes = new ArrayList<>();

		for (EntidadeFinanceira entidadeFinanceira : leilao.getEntidadesFinanceiras()) {
			entidadeFinanceirasNomes.add(entidadeFinanceira.getEntfinNome());
		}

		return new LeilaoDto(
				leilao.getLeiId(),
				leilao.getLeiDataOcorrencia(),
				leilao.getLeiDataVisitacao(),
				leilao.getLeiEndereco(),
				leilao.getLeiCidade(),
				leilao.getLeiEstado(),
				leilao.getLeiEnderecoWeb(),
				entidadeFinanceirasNomes
		);
	}
}
