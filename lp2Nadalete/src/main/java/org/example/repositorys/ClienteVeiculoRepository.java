package org.example.repositorys;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.ClienteVeiculos;
import org.example.models.Veiculos;

import java.util.List;

@Repository
public interface ClienteVeiculoRepository extends JpaRepository<ClienteVeiculos, Integer> {

	List<ClienteVeiculos> findByVeiculo(Veiculos veiculos);
}

