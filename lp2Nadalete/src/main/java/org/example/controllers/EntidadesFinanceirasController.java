package org.example.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import org.example.dtos.EntidadesFinanceirasDto;
import org.example.dtos.EntidadesFinanceirasForm;
import org.example.services.EntidadesFinanceirasService;

import java.util.List;

@Controller("/api/entidadesFinanceiras")
@CrossOrigin(value = "*")
public class EntidadesFinanceirasController {

    @Inject
    private EntidadesFinanceirasService entidadesFinanceirasService;

    @Get
    public HttpResponse<List<EntidadesFinanceirasDto>> getAll() {
        return entidadesFinanceirasService.getAll();
    }

    @Post
    public HttpResponse<EntidadesFinanceirasDto> save(@Body EntidadesFinanceirasForm entidadesFinanceirasForm) {
        return entidadesFinanceirasService.save(entidadesFinanceirasForm);
    }

    @Put("/{id}")
    public HttpResponse<EntidadesFinanceirasDto> update(@Body EntidadesFinanceirasForm entidadesFinanceirasForm, @PathVariable Integer id) {
        return entidadesFinanceirasService.update(entidadesFinanceirasForm, id);
    }

    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable Integer id) {
        entidadesFinanceirasService.delete(id);
        return HttpResponse.noContent();
    }

    @Get("/{id}")
    public HttpResponse<EntidadesFinanceirasDto> getById(@PathVariable Integer id) {
        return entidadesFinanceirasService.getById(id);
    }
}
