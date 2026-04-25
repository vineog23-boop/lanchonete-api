package br.com.centrallanches.lanchonete_api.repository;

import br.com.centrallanches.lanchonete_api.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository  extends JpaRepository<Endereco, UUID> {
}
