package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.PedidoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.PedidoResponse;
import br.com.centrallanches.lanchonete_api.entity.Cliente;
import br.com.centrallanches.lanchonete_api.entity.Endereco;
import br.com.centrallanches.lanchonete_api.entity.Pedido;
import br.com.centrallanches.lanchonete_api.entity.enums.StatusPedido;
import br.com.centrallanches.lanchonete_api.repository.ClienteRepository;
import br.com.centrallanches.lanchonete_api.repository.EnderecoRepository;
import br.com.centrallanches.lanchonete_api.repository.PedidoRepository;
import br.com.centrallanches.lanchonete_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoResponse create(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Endereco endereco = enderecoRepository.findById(request.enderecoId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Pedido entity = new Pedido();
        entity.setCliente(cliente);
        entity.setEndereco(endereco);
        entity.setStatus(StatusPedido.RECEBIDO);
        entity.setValorTotal(BigDecimal.ZERO);

        Pedido savedEntity = pedidoRepository.save(entity);

        return new PedidoResponse(
                savedEntity.getId(),
                savedEntity.getCliente().getId(),
                savedEntity.getEndereco().getId(),
                savedEntity.getStatus(),
                savedEntity.getValorTotal(),
                new ArrayList<>()
        );
    }

    public List<PedidoResponse> findAll() {
        return pedidoRepository.findAll()
                .stream()
                .map(entity -> new PedidoResponse(
                        entity.getId(),
                        entity.getCliente().getId(),
                        entity.getEndereco().getId(),
                        entity.getStatus(),
                        entity.getValorTotal(),
                        new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public PedidoResponse findById(UUID id) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com ID " + id + " não encontrado"));

        return new PedidoResponse(
                entity.getId(),
                entity.getCliente().getId(),
                entity.getEndereco().getId(),
                entity.getStatus(),
                entity.getValorTotal(),
                new ArrayList<>()
        );
    }

    public PedidoResponse update(UUID id, PedidoRequest request) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com ID " + id + " não encontrado para atualização"));

        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Endereco endereco = enderecoRepository.findById(request.enderecoId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        entity.setCliente(cliente);
        entity.setEndereco(endereco);

        Pedido savedEntity = pedidoRepository.save(entity);

        return new PedidoResponse(
                savedEntity.getId(),
                savedEntity.getCliente().getId(),
                savedEntity.getEndereco().getId(),
                savedEntity.getStatus(),
                savedEntity.getValorTotal(),
                new ArrayList<>()
        );
    }

    public void delete(UUID id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido com ID " + id + " não encontrado para exclusão");
        }
        pedidoRepository.deleteById(id);
    }
}
