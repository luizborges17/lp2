package org.example.repositorys;


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.EntidadeFinanceira;

@Repository
public interface EntidadesFinanceirasRepository extends JpaRepository<EntidadeFinanceira, Integer> {
}


