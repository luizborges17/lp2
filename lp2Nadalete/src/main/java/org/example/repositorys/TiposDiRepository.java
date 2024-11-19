package org.example.repositorys;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.TiposDi;

@Repository
public interface TiposDiRepository extends JpaRepository<TiposDi, Integer> {
}

