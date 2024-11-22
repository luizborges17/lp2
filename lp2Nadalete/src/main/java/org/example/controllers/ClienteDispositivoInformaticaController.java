package org.example.controllers;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import org.example.dtos.ClienteDispositivoInformaticaDto;
import org.example.dtos.ClienteDispositivoInformaticaForm;
import org.example.services.ClienteDispositivoInformaticaService;

import java.util.List;

@Controller("/api/lanceDi")
@CrossOrigin(value = "*")
public class ClienteDispositivoInformaticaController {

	@Inject
	private ClienteDispositivoInformaticaService clienteDispositivoInformaticaService;

	@Get
	public List<ClienteDispositivoInformaticaDto> getAll() {
		return clienteDispositivoInformaticaService.getAll();
	}

	@Post
	public ClienteDispositivoInformaticaDto save(@Body ClienteDispositivoInformaticaForm clienteDispositivoInformaticaForm) {
		return clienteDispositivoInformaticaService.save(clienteDispositivoInformaticaForm);
	}

	@Put("/{id}")
	public ClienteDispositivoInformaticaDto update(@Body ClienteDispositivoInformaticaForm clienteDispositivoInformaticaForm, @PathVariable Integer id) {
		return clienteDispositivoInformaticaService.update(clienteDispositivoInformaticaForm, id);
	}

	@Delete("/{id}")
	public HttpResponse<Void> delete(@PathVariable Integer id) {
		clienteDispositivoInformaticaService.delete(id);
		return HttpResponse.noContent();
	}

	@Get("/{id}")
	public ClienteDispositivoInformaticaDto getById(@PathVariable Integer id) {
		return clienteDispositivoInformaticaService.getById(id);
	}
}
