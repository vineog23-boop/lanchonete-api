package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.PedidoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.PedidoResponse;
import br.com.centrallanches.lanchonete_api.entity.Pedido;
import br.com.centrallanches.lanchonete_api.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service responsável pela lógica de negócio de Pedidos.
 */
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    /**
     * Cria um novo pedido.
     * @param request Dados do pedido.
     * @return PedidoResponse com os dados salvos.
     */
    public PedidoResponse create(PedidoRequest request) {
        Pedido entity = new Pedido();
        // TODO: Mapear cliente e endereco corretamente (provavelmente via repository)
        entity.setCliente(request.clienteId());
        entity.setEndereco(request.enderecoId());

        Pedido savedEntity = pedidoRepository.save(entity);

        return new PedidoResponse(
                savedEntity.getId(),
                savedEntity.getCliente(),
                savedEntity.getEndereco(),
                savedEntity.getItens(),
                savedEntity.getStatus(),
                savedEntity.getValorTotal()
        );
    }

    /**
     * Lista todos os pedidos.
     * @return Lista de PedidoResponse.
     */
    public List<PedidoResponse> findAll() {
        return pedidoRepository.findAll()
                .stream()
                .map(entity -> new PedidoResponse(
                        entity.getId(),
                        entity.getCliente(),
                        entity.getEndereco(),
                        entity.getItens(),
                        entity.getStatus(),
                        entity.getValorTotal()))
                .collect(Collectors.toList());
    }

    /**
     * Busca um pedido por ID.
     * @param id ID do pedido.
     * @return PedidoResponse correspondente.
     */
    public PedidoResponse findById(UUID id) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com ID " + id + " não encontrado"));

        return new PedidoResponse(
                entity.getId(),
                entity.getCliente(),
                entity.getEndereco(),
                entity.getItens(),
                entity.getStatus(),
                entity.getValorTotal()
        );
    }

    /**
     * Atualiza um pedido existente.
     * @param id ID do pedido.
     * @param request Novos dados.
     * @return PedidoResponse atualizado.
     */
    public PedidoResponse update(UUID id, PedidoRequest request) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com ID " + id + " não encontrado para atualização"));

        entity.setCliente(request.clienteId());
        entity.setEndereco(request.enderecoId());

        Pedido savedEntity = pedidoRepository.save(entity);

        return new PedidoResponse(
                savedEntity.getId(),
                savedEntity.getCliente(),
                savedEntity.getEndereco(),
                savedEntity.getItens(),
                savedEntity.getStatus(),
                savedEntity.getValorTotal()
        );
    }

    /**
     * Remove um pedido.
     * @param id ID do pedido a remover.
     */
    public void delete(UUID id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido com ID " + id + " não encontrado para exclusão");
        }
        pedidoRepository.deleteById(id);
    }
}
