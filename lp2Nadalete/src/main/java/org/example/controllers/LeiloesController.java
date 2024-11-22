package org.example.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import org.example.dtos.LeilaoDto;
import org.example.dtos.LeilaoForm;
import org.example.services.LeilaoService;

import java.util.List;

@Controller("/api/leilao")
@CrossOrigin(value = "*")
public class LeiloesController {

	@Inject
	private LeilaoService leilaoService;

	@Get
	public HttpResponse<List<LeilaoDto>> getAll() {
		return leilaoService.getAll();
	}

	@Get("/dataOcorrencia")
	public HttpResponse<List<LeilaoDto>> getAllOrderByDataOcorrencia() {
		return leilaoService.getAllOrderByDataOcorrencia();
	}

	@Post
	public HttpResponse<LeilaoDto> save(@Body LeilaoForm leilaoForm) {
		return leilaoService.save(leilaoForm);
	}

	@Put("/{id}")
	public HttpResponse<LeilaoDto> update(@Body LeilaoForm leilaoForm, @PathVariable Integer id) {
		return leilaoService.update(leilaoForm, id);
	}

	@Delete("/{id}")
	public HttpResponse<Void> delete(@PathVariable Integer id) {
		leilaoService.delete(id);
		return HttpResponse.noContent();
	}

	@Get("/{id}")
	public HttpResponse<LeilaoDto> getById(@PathVariable Integer id) {
		return leilaoService.getById(id);
	}
}
