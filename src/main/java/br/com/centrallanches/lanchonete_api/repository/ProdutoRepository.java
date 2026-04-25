package br.com.centrallanches.lanchonete_api.repository;

import br.com.centrallanches.lanchonete_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Integer > {
}
