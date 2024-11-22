package org.example.repositorys;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.example.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

	Cliente findByCliCpf(String cpf);

	Long deleteByCliCpf(String cpf);
}
