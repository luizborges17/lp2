package org.example.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import org.example.dtos.ClienteVeiculoDto;
import org.example.dtos.ClienteVeiculoForm;
import org.example.services.ClienteVeiculoService;

import java.util.List;

@Controller("/api/lanceVeiculos")
@CrossOrigin(value = "*")
public class ClienteVeiculoController {

	@Inject
	private ClienteVeiculoService clienVeiculoService;

	@Get
	public HttpResponse<List<ClienteVeiculoDto>> getAll() {
		return clienVeiculoService.getAll();
	}

	@Post
	public HttpResponse<ClienteVeiculoDto> save(@Body ClienteVeiculoForm clienteVeiculoForm) {
		return clienVeiculoService.save(clienteVeiculoForm);
	}

	@Put("/{id}")
	public HttpResponse<ClienteVeiculoDto> update(@Body ClienteVeiculoForm clienteVeiculoForm, @PathVariable Integer id) {
		return clienVeiculoService.update(clienteVeiculoForm, id);
	}

	@Delete("/{id}")
	public HttpResponse<Void> delete(@PathVariable Integer id) {
		clienVeiculoService.delete(id);
		return HttpResponse.noContent();
	}

	@Get("/{id}")
	public HttpResponse<ClienteVeiculoDto> getById(@PathVariable Integer id) {
		return clienVeiculoService.getById(id);
	}
}
