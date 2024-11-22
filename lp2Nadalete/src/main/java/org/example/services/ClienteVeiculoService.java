package org.example.services;


import io.micronaut.http.HttpResponse;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import org.example.dtos.ClienteVeiculoDto;
import org.example.dtos.ClienteVeiculoForm;
import org.example.models.Cliente;
import org.example.models.ClienteVeiculos;
import org.example.models.Veiculos;
import org.example.repositorys.ClienteRepository;
import org.example.repositorys.ClienteVeiculoRepository;
import org.example.repositorys.VeiculosRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ClienteVeiculoService {

	@Inject
	private ClienteVeiculoRepository ClienteVeiculoRepository;

	@Inject
	private ClienteRepository ClienteRepository;

	@Inject
	private VeiculosRepository veiculosRepository;

	public HttpResponse<List<ClienteVeiculoDto>> getAll() {
		List<ClienteVeiculos> ClienteVeiculos = ClienteVeiculoRepository.findAll();

		List<ClienteVeiculoDto> ClienteVeiculoDtos = new ArrayList<>();

		for (ClienteVeiculos ClienteVeiculo : ClienteVeiculos) {
			ClienteVeiculoDtos.add(converteParaDto(ClienteVeiculo));
		}
		return HttpResponse.ok(ClienteVeiculoDtos);
	}

	public HttpResponse<ClienteVeiculoDto> save(ClienteVeiculoForm ClienteVeiculoForm) {

		Cliente Cliente = ClienteRepository.findByCliCpf(ClienteVeiculoForm.getCpfCliente());

		Veiculos veiculos = veiculosRepository.findById(ClienteVeiculoForm.getVeiculo())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + ClienteVeiculoForm.getVeiculo() + " na classe: " + Veiculos.class.toString()));

		ClienteVeiculos ClienteVeiculos = new ClienteVeiculos(
				veiculos,
				Cliente,
				ClienteVeiculoForm.getValorLance(),
				LocalDateTime.now()
		);

		return HttpResponse.ok(converteParaDto(ClienteVeiculoRepository.save(ClienteVeiculos)));
	}

	public HttpResponse<ClienteVeiculoDto> update(ClienteVeiculoForm ClienteVeiculoForm, Integer id) {

		ClienteVeiculos ClienteVeiculos = ClienteVeiculoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + ClienteVeiculos.class.toString()));

		Cliente Cliente = ClienteRepository.findByCliCpf(ClienteVeiculoForm.getCpfCliente());

		Veiculos veiculos = veiculosRepository.findById(ClienteVeiculoForm.getVeiculo())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + ClienteVeiculoForm.getVeiculo() + " na classe: " + Veiculos.class.toString()));

		ClienteVeiculos.setCliveiValorLance(ClienteVeiculoForm.getValorLance());
		ClienteVeiculos.setCliente(Cliente);
		ClienteVeiculos.setVeiculo(veiculos);

		return HttpResponse.ok(converteParaDto(ClienteVeiculoRepository.save(ClienteVeiculos)));
	}

	@Transactional
	public HttpResponse<Void> delete(Integer id) {
		ClienteVeiculoRepository.deleteById(id);
		return HttpResponse.noContent();
	}

	public HttpResponse<ClienteVeiculoDto> getById(Integer id) {
		return HttpResponse.ok(converteParaDto(ClienteVeiculoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + ClienteVeiculos.class.toString()))));
	}

	public ClienteVeiculoDto converteParaDto(ClienteVeiculos ClienteVeiculos) {
		return new ClienteVeiculoDto(
				ClienteVeiculos.getCliveiId(),
				ClienteVeiculos.getVeiculo().getVeiId(),
				ClienteVeiculos.getCliente().getCliNome(),
				ClienteVeiculos.getCliente().getCliCpf(),
				ClienteVeiculos.getCliveiValorLance()
		);
	}
}
