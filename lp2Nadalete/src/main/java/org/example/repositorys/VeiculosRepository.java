package org.example.repositorys;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.Veiculos;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos, Integer> {
}

