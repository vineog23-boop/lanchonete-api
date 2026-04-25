package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.entity.Pedido;
import br.com.centrallanches.lanchonete_api.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public Pedido create (Pedido pedido) {                      //  MÉTODO CREATE
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {                            // MÉTODO READ ALL
        return pedidoRepository.findAll();
    }

    public Pedido findById(UUID id) {                       // MÉTODO READ ID
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O pedido : " + ( id ) + " não foi encontrado"));
    }

    public Pedido update(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void delete(UUID id) {
        pedidoRepository.deleteById(id);
    }
}

