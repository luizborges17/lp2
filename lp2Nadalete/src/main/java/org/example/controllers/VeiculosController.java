package org.example.controllers;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import org.example.dtos.VeiculosDto;
import org.example.dtos.VeiculosForm;
import org.example.services.VeiculosService;

import java.util.List;


@Controller("/api/veiculos")
@CrossOrigin(value = "*")
public class VeiculosController {

    @Inject
    private VeiculosService veiculosService;

    @Get
    // Todos as informações do Veículos
    public List<VeiculosDto> getAll() {
        return veiculosService.getAll();
    }

    @Post
    public VeiculosDto save(@Body VeiculosForm veiculosForm) {
        return veiculosService.save(veiculosForm);
    }

    @Put("/{id}")
    public VeiculosDto update(@Body VeiculosForm veiculosForm, @PathVariable Integer id) {
        return veiculosService.update(veiculosForm, id);
    }

    @Delete("/{id}")
    public HttpResponse<Void> delete(@PathVariable Integer id) {
        veiculosService.delete(id);
        return HttpResponse.noContent();
    }

    @Get("/{id}")
    public VeiculosDto getById(@PathVariable Integer id) {
        return veiculosService.getById(id);
    }
}
