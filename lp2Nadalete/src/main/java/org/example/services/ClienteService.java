package org.example.services;

import io.micronaut.data.exceptions.EmptyResultException;
import io.micronaut.http.HttpResponse;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.example.dtos.ClienteDto;
import org.example.dtos.ClienteForm;
import org.example.exceptions.ClienteJaCadastradoException;
import org.example.models.Cliente;
import org.example.repositorys.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ClienteService {

	@Inject
	private ClienteRepository clienteRepository;

	public HttpResponse<List<ClienteDto>> getAll() {
		List<Cliente> Clientes = clienteRepository.findAll();
		List<ClienteDto> ClienteDtos = new ArrayList<>();

		for (Cliente Cliente : Clientes) {
			ClienteDtos.add(converteParaDto(Cliente));
		}
		return HttpResponse.ok(ClienteDtos);
	}

	public HttpResponse<ClienteDto> save(ClienteForm ClienteForm) {

		try {
			if (clienteRepository.findByCliCpf(ClienteForm.getCliCpf()) != null) {
				throw new ClienteJaCadastradoException("Já existe Cliente cadastrado com o cpf: " + ClienteForm.getCliCpf());
			}
		}catch(EmptyResultException e){

		}

		Cliente ClienteNovo = new Cliente(
				ClienteForm.getCliCpf(),
				ClienteForm.getCliNome(),
				ClienteForm.getCliEmail()
		);

		return HttpResponse.ok(converteParaDto(clienteRepository.save(ClienteNovo)));
	}

	public HttpResponse<ClienteDto> update(ClienteForm ClienteForm, String cpf) {

		Cliente Cliente = clienteRepository.findByCliCpf(cpf);

		Cliente.setCliNome(ClienteForm.getCliNome());
		Cliente.setCliEmail(ClienteForm.getCliEmail());

		return HttpResponse.ok(converteParaDto(clienteRepository.save(Cliente)));
	}

	@Transactional
	public HttpResponse<Void> delete(String cpf) {
		clienteRepository.deleteByCliCpf(cpf);
		return HttpResponse.noContent();
	}

	public HttpResponse<ClienteDto> getById(String cpf) {
		return HttpResponse.ok(converteParaDto(clienteRepository.findByCliCpf(cpf)));
	}

	public ClienteDto converteParaDto(Cliente Cliente) {
		return new ClienteDto(Cliente.getCliCpf(), Cliente.getCliNome(), Cliente.getCliEmail());
	}
}
