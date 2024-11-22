package org.example.services;


import io.micronaut.http.HttpResponse;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import org.example.dtos.DispositivoInformaticaDto;
import org.example.dtos.DispositivoInformaticaForm;
import org.example.exceptions.DispositivosInformaticaTemLancesException;
import org.example.models.ClienteDispositivoInformatica;
import org.example.models.DispositivoInformatica;
import org.example.models.Leilao;
import org.example.models.TiposDi;
import org.example.repositorys.ClienteDispositivoInformaticaRepository;
import org.example.repositorys.DispositivosInformaticaRepository;
import org.example.repositorys.LeilaoRepository;
import org.example.repositorys.TiposDiRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DispositivosInformaticaService {

	@Inject
	private DispositivosInformaticaRepository dispositivosInformaticaRepository;

	@Inject
	private ClienteDispositivoInformaticaRepository clienteDispositivoInformaticaRepository;

	@Inject
	private TiposDiRepository tipoDiRepository;

	@Inject
	private LeilaoRepository leilaoRepository;

	public HttpResponse<List<DispositivoInformaticaDto>> getAll() {
		List<DispositivoInformatica> dispositivoInformaticas = dispositivosInformaticaRepository.findAll();
		List<DispositivoInformaticaDto> dispositivoInformaticaDtos = new ArrayList<>();

		for (DispositivoInformatica dispositivoInformatica : dispositivoInformaticas) {
			dispositivoInformaticaDtos.add(converteParaDto(dispositivoInformatica));
		}
		return HttpResponse.ok(dispositivoInformaticaDtos);
	}

	public HttpResponse<DispositivoInformaticaDto> save(DispositivoInformaticaForm dispositivoInformaticaForm) {

		TiposDi tipoDi = tipoDiRepository.findById(dispositivoInformaticaForm.getTipoDi())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + dispositivoInformaticaForm.getTipoDi() + " na classe: " + TiposDi.class.toString()));

		Leilao leilao = leilaoRepository.findById(dispositivoInformaticaForm.getLeilao())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + dispositivoInformaticaForm.getLeilao() + " na classe: " + Leilao.class.toString()));

		LocalDateTime dataAtual = LocalDateTime.now();

		if (!dataAtual.isBefore(leilao.getLeiDataOcorrencia())) {
			throw new DispositivosInformaticaTemLancesException("O leilão que está cadastrando no dispositivo de informática já encerrou !!!");
		}

		DispositivoInformatica dispositivoInformatica = new DispositivoInformatica(
				dispositivoInformaticaForm.getDiEnderecoFisico(),
				dispositivoInformaticaForm.getDiMarca(),
				dispositivoInformaticaForm.getDiProcessador(),
				dispositivoInformaticaForm.getDiTela(),
				dispositivoInformaticaForm.getDiArmazenamento(),
				dispositivoInformaticaForm.getDiMemoria(),
				dispositivoInformaticaForm.getDiTensao(),
				dispositivoInformaticaForm.getDiNumeroPortas(),
				tipoDi,
				leilao
		);

		return HttpResponse.ok(converteParaDto(dispositivosInformaticaRepository.save(dispositivoInformatica)));
	}

	public HttpResponse<DispositivoInformaticaDto> update(DispositivoInformaticaForm dispositivoInformaticaForm, Integer id) {

		DispositivoInformatica dispositivoInformatica = dispositivosInformaticaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + DispositivoInformatica.class.toString()));

		List<ClienteDispositivoInformatica> lances = clienteDispositivoInformaticaRepository.findByDispositivoInformatica(dispositivoInformatica);

		Leilao leilao = leilaoRepository.findById(dispositivoInformaticaForm.getLeilao())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + dispositivoInformaticaForm.getLeilao() + " na classe: " + Leilao.class.toString()));

		if (!lances.isEmpty() && dispositivoInformatica.getLeilao().getLeiId() != dispositivoInformaticaForm.getLeilao()) {
			throw new DispositivosInformaticaTemLancesException("O dispositivo de informática que está tentando alterar o leilão já possui lances registrados!!!");
		}

		LocalDateTime dataAtual = LocalDateTime.now();

		if (dispositivoInformatica.getLeilao().getLeiDataOcorrencia() != leilao.getLeiDataOcorrencia() && !dataAtual.isBefore(leilao.getLeiDataOcorrencia())) {
			throw new DispositivosInformaticaTemLancesException("O leilão que está cadastrando no dispositivo de informática já encerrou !!!");
		}

		TiposDi tipoDi = tipoDiRepository.findById(dispositivoInformaticaForm.getTipoDi())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + dispositivoInformaticaForm.getTipoDi() + " na classe: " + TiposDi.class.toString()));

		dispositivoInformatica.setDiArmazenamento(dispositivoInformaticaForm.getDiArmazenamento());
		dispositivoInformatica.setDiEnderecoFisico(dispositivoInformaticaForm.getDiEnderecoFisico());
		dispositivoInformatica.setDiMarca(dispositivoInformaticaForm.getDiMarca());
		dispositivoInformatica.setDiMemoria(dispositivoInformaticaForm.getDiMemoria());
		dispositivoInformatica.setDiNumeroPortas(dispositivoInformaticaForm.getDiNumeroPortas());
		dispositivoInformatica.setDiProcessador(dispositivoInformaticaForm.getDiProcessador());
		dispositivoInformatica.setDiTela(dispositivoInformaticaForm.getDiTela());
		dispositivoInformatica.setDiTensao(dispositivoInformaticaForm.getDiTensao());
		dispositivoInformatica.setTipoDi(tipoDi);
		dispositivoInformatica.setLeilao(leilao);

		return HttpResponse.ok(converteParaDto(dispositivosInformaticaRepository.save(dispositivoInformatica)));
	}

	@Transactional
	public HttpResponse<Void> delete(Integer id) {

		dispositivosInformaticaRepository.deleteById(id);
		return HttpResponse.noContent();
	}

	public HttpResponse<DispositivoInformaticaDto> getById(Integer id) {
		return HttpResponse.ok(converteParaDto(dispositivosInformaticaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + DispositivoInformatica.class.toString()))));
	}

	private DispositivoInformaticaDto converteParaDto(DispositivoInformatica dispositivoInformatica) {
		return new DispositivoInformaticaDto(
				dispositivoInformatica.getDiId(),
				dispositivoInformatica.getDiEnderecoFisico(),
				dispositivoInformatica.getDiMarca(),
				dispositivoInformatica.getDiProcessador(),
				dispositivoInformatica.getDiTela(),
				dispositivoInformatica.getDiArmazenamento(),
				dispositivoInformatica.getDiMemoria(),
				dispositivoInformatica.getDiTensao(),
				dispositivoInformatica.getDiNumeroPortas(),
				dispositivoInformatica.getTipoDi().getTdiNome(),
				dispositivoInformatica.getLeilao().getLeiDataOcorrencia());
	}
}
