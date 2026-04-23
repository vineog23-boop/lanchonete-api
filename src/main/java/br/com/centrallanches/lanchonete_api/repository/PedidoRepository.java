package br.com.centrallanches.lanchonete_api.repository;

import br.com.centrallanches.lanchonete_api.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository  extends JpaRepository<Pedido, UUID> {
}
