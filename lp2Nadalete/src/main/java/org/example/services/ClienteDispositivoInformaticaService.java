package org.example.services;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import org.example.dtos.ClienteDispositivoInformaticaDto;
import org.example.dtos.ClienteDispositivoInformaticaForm;
import org.example.models.Cliente;
import org.example.models.ClienteDispositivoInformatica;
import org.example.models.DispositivoInformatica;
import org.example.repositorys.ClienteDispositivoInformaticaRepository;
import org.example.repositorys.ClienteRepository;
import org.example.repositorys.DispositivosInformaticaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ClienteDispositivoInformaticaService {

	@Inject
	private ClienteDispositivoInformaticaRepository clienteDispositivosInformaticaRepository;

	@Inject
	private ClienteRepository clienteRepository;

	@Inject
	private DispositivosInformaticaRepository dispositivosInformaticaRepository;

	public List<ClienteDispositivoInformaticaDto> getAll() {
		List<ClienteDispositivoInformatica> cliDispositivoInformaticas = clienteDispositivosInformaticaRepository.findAll();

		List<ClienteDispositivoInformaticaDto> clientesdispositivoInformaticaDtos = new ArrayList<>();

		for (ClienteDispositivoInformatica clienteDispositivoInformatica : cliDispositivoInformaticas) {
			clientesdispositivoInformaticaDtos.add(converteParaDto(clienteDispositivoInformatica));
		}
		return clientesdispositivoInformaticaDtos;
	}

	public ClienteDispositivoInformaticaDto save(ClienteDispositivoInformaticaForm clienteDispositivoInformaticaForm) {

		Cliente cliente = clienteRepository.findByCliCpf(clienteDispositivoInformaticaForm.getCpfCliente());

		DispositivoInformatica dispositivoInformatica = dispositivosInformaticaRepository.findById(clienteDispositivoInformaticaForm.getDispositivoInformatica())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + clienteDispositivoInformaticaForm.getDispositivoInformatica()));

		ClienteDispositivoInformatica clienteDispositivoInformatica = new ClienteDispositivoInformatica(
				clienteDispositivoInformaticaForm.getValor(),
				cliente,
				dispositivoInformatica,
				LocalDateTime.now()
		);

		return converteParaDto(clienteDispositivosInformaticaRepository.save(clienteDispositivoInformatica));
	}

	public ClienteDispositivoInformaticaDto update(ClienteDispositivoInformaticaForm clienteDispositivoInformaticaForm, Integer id) {

		ClienteDispositivoInformatica clienteDispositivoInformatica = clienteDispositivosInformaticaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id));

		Cliente cliente = clienteRepository.findByCliCpf(clienteDispositivoInformaticaForm.getCpfCliente());

		DispositivoInformatica dispositivoInformatica = dispositivosInformaticaRepository.findById(clienteDispositivoInformaticaForm.getDispositivoInformatica())
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + clienteDispositivoInformaticaForm.getDispositivoInformatica()));

		clienteDispositivoInformatica.setClidiValorLance(clienteDispositivoInformaticaForm.getValor());
		clienteDispositivoInformatica.setCliente(cliente);
		clienteDispositivoInformatica.setDispositivoInformatica(dispositivoInformatica);

		return converteParaDto(clienteDispositivosInformaticaRepository.save(clienteDispositivoInformatica));
	}

	public void delete(Integer id) {
		clienteDispositivosInformaticaRepository.deleteById(id);
	}

	public ClienteDispositivoInformaticaDto getById(Integer id) {
		return converteParaDto(clienteDispositivosInformaticaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id)));
	}

	private ClienteDispositivoInformaticaDto converteParaDto(ClienteDispositivoInformatica clienteDispositivoInformatica) {
		return new ClienteDispositivoInformaticaDto(
				clienteDispositivoInformatica.getClidiId(),
				clienteDispositivoInformatica.getClidiValorLance(),
				clienteDispositivoInformatica.getCliente().getCliNome(),
				clienteDispositivoInformatica.getCliente().getCliCpf(),
				clienteDispositivoInformatica.getDispositivoInformatica().getDiId(),
				clienteDispositivoInformatica.getClidiDataHoraLance()
		);
	}
}
