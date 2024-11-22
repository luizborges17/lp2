package org.example.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import org.example.dtos.DispositivoInformaticaDto;
import org.example.dtos.DispositivoInformaticaForm;
import org.example.services.DispositivosInformaticaService;

import java.util.List;

@Controller("/api/dispositivosinformatica")
@CrossOrigin(value = "*")
public class DispositivosInformaticaController {

	@Inject
	private DispositivosInformaticaService dispositivosInformaticaService;

	@Get
	public HttpResponse<List<DispositivoInformaticaDto>> getAll() {
		return dispositivosInformaticaService.getAll();
	}

	@Post
	public HttpResponse<DispositivoInformaticaDto> save(@Body DispositivoInformaticaForm dispositivoInformaticaForm) {
		return dispositivosInformaticaService.save(dispositivoInformaticaForm);
	}

	@Put("/{id}")
	public HttpResponse<DispositivoInformaticaDto> update(@Body DispositivoInformaticaForm dispositivoInformaticaForm, @PathVariable Integer id) {
		return dispositivosInformaticaService.update(dispositivoInformaticaForm, id);
	}

	@Delete("/{id}")
	public HttpResponse<Void> delete(@PathVariable Integer id) {
		dispositivosInformaticaService.delete(id);
		return HttpResponse.noContent();
	}

	@Get("/{id}")
	public HttpResponse<DispositivoInformaticaDto> getById(@PathVariable Integer id) {
		return dispositivosInformaticaService.getById(id);
	}
}
