package org.example.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import org.example.dtos.ClienteDto;
import org.example.dtos.ClienteForm;
import org.example.services.ClienteService;

import java.util.List;

@Controller("/api/cliente")
@CrossOrigin(value = "*")
public class ClienteController {

	@Inject
	private ClienteService clienteService;

	@Get
	public HttpResponse<List<ClienteDto>> getAll() {
		return clienteService.getAll();
	}

	@Post
	public HttpResponse<ClienteDto> save(@Body ClienteForm clienteForm) {
		return clienteService.save(clienteForm);
	}

	@Put("/{cpf}")
	public HttpResponse<ClienteDto> update(@Body ClienteForm clienteForm, @PathVariable String cpf) {
		return clienteService.update(clienteForm, cpf);
	}

	@Delete("/{cpf}")
	public HttpResponse<Void> delete(@PathVariable String cpf) {
		clienteService.delete(cpf);
		return HttpResponse.noContent();
	}

	@Get("/{cpf}")
	public HttpResponse<ClienteDto> getById(@PathVariable String cpf) {
		return clienteService.getById(cpf);
	}
}
