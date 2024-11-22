package org.example.services;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import org.example.dtos.VeiculosDto;
import org.example.dtos.VeiculosForm;
import org.example.exceptions.DispositivosInformaticaTemLancesException;
import org.example.models.ClienteVeiculos;
import org.example.models.Leilao;
import org.example.models.TiposVeiculos;
import org.example.models.Veiculos;
import org.example.repositorys.ClienteVeiculoRepository;
import org.example.repositorys.LeilaoRepository;
import org.example.repositorys.TiposVeiculosRepository;
import org.example.repositorys.VeiculosRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class VeiculosService {

	@Inject
	private VeiculosRepository veiculosRepository;

	@Inject
	private TiposVeiculosRepository tiposVeiculosRepository;

	@Inject
	private LeilaoRepository leilaoRepository;

	@Inject
	private ClienteVeiculoRepository clienteVeiculoRepository;


	public List<VeiculosDto> getAll() {
		List<Veiculos> veiculos = veiculosRepository.findAll();
		List<VeiculosDto> veiculosDto = new ArrayList<>();
		for (Veiculos veiculo : veiculos) {
			veiculosDto.add(converteParaDto(veiculo));
		}
		return veiculosDto;
	}

	public VeiculosDto getById(Integer id) {
		Veiculos veiculo = veiculosRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + Veiculos.class.toString()));
		return converteParaDto(veiculo);
	}

	public VeiculosDto save(VeiculosForm veiculosForm) {
		TiposVeiculos tiposVeiculos = tiposVeiculosRepository.findById(veiculosForm.getTipoVeiculo()).orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + veiculosForm.getTipoVeiculo() + " na classe: " + TiposVeiculos.class.toString()));

		Leilao leilao = leilaoRepository.findById(veiculosForm.getLeilao()).orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + veiculosForm.getLeilao() + " na classe: " + Leilao.class.toString()));

		LocalDateTime dataAtual = LocalDateTime.now();
		if (!dataAtual.isBefore(leilao.getLeiDataOcorrencia())) {
			throw new DispositivosInformaticaTemLancesException("O leilão que está cadastrando no dispositivo de informática já encerrou !!!");
		}

		Veiculos veiculos = new Veiculos(
				veiculosForm.getVeiPlaca(),
				veiculosForm.getVeiMarca(),
				veiculosForm.getVeiAnoFabricacao(),
				veiculosForm.getVeiDistanciaRodada(),
				veiculosForm.getVeiCambio(),
				veiculosForm.getVeiCor(),
				veiculosForm.getVeiCor(),
				veiculosForm.getVeiPeso(),
				tiposVeiculos,
				leilao
		);
		veiculos = veiculosRepository.save(veiculos);
		return converteParaDto(veiculos);
	}

	public VeiculosDto update(VeiculosForm veiculosForm, Integer id) {
		Veiculos veiculo = veiculosRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + Veiculos.class.toString()));

		Leilao leilao = leilaoRepository.findById(veiculosForm.getLeilao()).orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + veiculosForm.getLeilao() + " na classe: " + Leilao.class.toString()));

		List<ClienteVeiculos> lances = clienteVeiculoRepository.findByVeiculo(veiculo);

		TiposVeiculos tiposVeiculos = tiposVeiculosRepository.findById(veiculosForm.getTipoVeiculo()).orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + veiculosForm.getTipoVeiculo() + " na classe: " + TiposVeiculos.class.toString()));

		if (!lances.isEmpty() && veiculo.getLeilao().getLeiId() != veiculosForm.getLeilao()) {
			throw new DispositivosInformaticaTemLancesException("O veiculo que está tentando alterar o leilão já possui lances registrados!!!");
		}

		LocalDateTime dataAtual = LocalDateTime.now();
		if (veiculo.getLeilao().getLeiDataOcorrencia() != leilao.getLeiDataOcorrencia() && !dataAtual.isBefore(leilao.getLeiDataOcorrencia())) {
			throw new DispositivosInformaticaTemLancesException("O leilão que está cadastrando no veiculo já encerrou !!!");
		}

		veiculo.setLeilao(leilao);
		veiculo.setTipoVeiculo(tiposVeiculos);
		veiculo.setVeiAnoFabricacao(veiculosForm.getVeiAnoFabricacao());
		veiculo.setVeiCambio(veiculosForm.getVeiCambio());
		veiculo.setVeiCombustivel(veiculosForm.getVeiCombustivel());
		veiculo.setVeiCor(veiculosForm.getVeiCor());
		veiculo.setVeiDistanciaRodada(veiculosForm.getVeiDistanciaRodada());
		veiculo.setVeiMarca(veiculosForm.getVeiMarca());
		veiculo.setVeiPeso(veiculosForm.getVeiPeso());
		veiculo.setVeiPlaca(veiculosForm.getVeiPlaca());

		veiculo = veiculosRepository.save(veiculo);
		return converteParaDto(veiculo);
	}

	public void delete(Integer id) {
		veiculosRepository.deleteById(id);
	}

	public VeiculosDto converteParaDto(Veiculos veiculos) {
		return new VeiculosDto(
				veiculos.getVeiId(),
				veiculos.getVeiPlaca(),
				veiculos.getVeiMarca(),
				veiculos.getVeiAnoFabricacao(),
				veiculos.getVeiDistanciaRodada(),
				veiculos.getVeiCambio(),
				veiculos.getVeiCombustivel(),
				veiculos.getVeiCor(),
				veiculos.getVeiPeso(),
				veiculos.getTipoVeiculo().getTveiNome(),
				veiculos.getLeilao().getLeiDataOcorrencia()
		);
	}
}
