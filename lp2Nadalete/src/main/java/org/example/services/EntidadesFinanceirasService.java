package org.example.services;

import io.micronaut.http.HttpResponse;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import org.example.dtos.EntidadesFinanceirasDto;
import org.example.dtos.EntidadesFinanceirasForm;
import org.example.models.EntidadeFinanceira;
import org.example.repositorys.EntidadesFinanceirasRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class EntidadesFinanceirasService {

    @Inject
    private EntidadesFinanceirasRepository entidadesFinanceirasRepository;

    // GetAll
    public HttpResponse<List<EntidadesFinanceirasDto>> getAll() {
        List<EntidadeFinanceira> entidadesFinanceiras = entidadesFinanceirasRepository.findAll();
        List<EntidadesFinanceirasDto> entidadesFinanceirasDto = new ArrayList<>();

        for (EntidadeFinanceira entidadesFinanceira : entidadesFinanceiras) {
            entidadesFinanceirasDto.add(converteParaDto(entidadesFinanceira));
        }
        return HttpResponse.ok(entidadesFinanceirasDto);
    }

    // GetById
    public HttpResponse<EntidadesFinanceirasDto> getById(Integer id) {
        return HttpResponse.ok(converteParaDto(
                entidadesFinanceirasRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: " + EntidadeFinanceira.class.toString()))
        ));
    }

    // Post
    @Transactional
    public HttpResponse<EntidadesFinanceirasDto> save(EntidadesFinanceirasForm entidadesFinanceirasForm) {
        EntidadeFinanceira entidadesFinan = new EntidadeFinanceira();
        entidadesFinan.setEntfinNome(entidadesFinanceirasForm.getEntfinNome());
        return HttpResponse.ok(converteParaDto(entidadesFinanceirasRepository.save(entidadesFinan)));
    }

    // Put
    public HttpResponse<EntidadesFinanceirasDto> update(EntidadesFinanceirasForm entidadesFinanceirasForm, Integer id) {
        EntidadeFinanceira entidadesFinan = entidadesFinanceirasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não encontrado registro de id: " + id + " na classe: EntidadesFinanceirasService"));

        entidadesFinan.setEntfinNome(entidadesFinanceirasForm.getEntfinNome());

        return HttpResponse.ok(converteParaDto(entidadesFinanceirasRepository.saveAndFlush(entidadesFinan)));
    }

    // Delete
    @Transactional
    public HttpResponse<Void> delete(Integer id) {
        entidadesFinanceirasRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    // Converter
    public EntidadesFinanceirasDto converteParaDto(EntidadeFinanceira entidadesFinanceiras) {
        return new EntidadesFinanceirasDto(entidadesFinanceiras.getEntfinId(), entidadesFinanceiras.getEntfinNome());
    }
}
