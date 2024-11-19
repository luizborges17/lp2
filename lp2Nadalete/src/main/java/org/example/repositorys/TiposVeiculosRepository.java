package org.example.repositorys;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.TiposVeiculos;

@Repository
public interface TiposVeiculosRepository extends JpaRepository<TiposVeiculos, Integer> {
}
