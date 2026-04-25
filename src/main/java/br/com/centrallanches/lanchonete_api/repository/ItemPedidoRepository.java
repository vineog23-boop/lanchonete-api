package br.com.centrallanches.lanchonete_api.repository;

import br.com.centrallanches.lanchonete_api.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer > {
}
