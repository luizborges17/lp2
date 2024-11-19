package org.example.repositorys;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.Leilao;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Integer> {
}

