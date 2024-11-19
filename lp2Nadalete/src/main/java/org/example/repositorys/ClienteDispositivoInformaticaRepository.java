package org.example.repositorys;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.ClienteDispositivoInformatica;
import org.example.models.DispositivoInformatica;

import java.util.List;

@Repository
public interface ClienteDispositivoInformaticaRepository extends JpaRepository<ClienteDispositivoInformatica, Integer> {

	List<ClienteDispositivoInformatica> findByDispositivoInformatica(DispositivoInformatica dispositivoInformatica);
}
