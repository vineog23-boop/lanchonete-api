package br.com.centrallanches.lanchonete_api.repository;

import br.com.centrallanches.lanchonete_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
