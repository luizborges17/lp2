package org.example.repositorys;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.DispositivoInformatica;

@Repository
public interface DispositivosInformaticaRepository extends JpaRepository<DispositivoInformatica, Integer> {
}
