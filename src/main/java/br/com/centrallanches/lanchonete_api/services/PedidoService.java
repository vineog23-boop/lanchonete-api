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
import br.com.centrallanches.lanchonete_api.exception.ResourceNotFoundException; // Import adicionado
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
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado")); // Alterado
        Endereco endereco = enderecoRepository.findById(request.enderecoId())
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado")); // Alterado

        Pedido entity = new Pedido();
        entity.setCliente(cliente);
        entity.setEndereco(endereco);
        entity.setStatus(StatusPedido.RECEBIDO);
        entity.setValorTotal(BigDecimal.ZERO);

        Pedido savedEntity = pedidoRepository.save(entity);

        return toPedidoResponse(savedEntity);
    }

    public List<PedidoResponse> findAll() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toPedidoResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse findById(UUID id) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + id + " não encontrado")); // Alterado

        return toPedidoResponse(entity);
    }

    public PedidoResponse update(UUID id, PedidoRequest request) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido com ID " + id + " não encontrado para atualização")); // Alterado

        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado")); // Alterado
        Endereco endereco = enderecoRepository.findById(request.enderecoId())
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado")); // Alterado

        entity.setCliente(cliente);
        entity.setEndereco(endereco);

        Pedido savedEntity = pedidoRepository.save(entity);

        return toPedidoResponse(savedEntity);
    }

    public void delete(UUID id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido com ID " + id + " não encontrado para exclusão"); // Alterado
        }
        pedidoRepository.deleteById(id);
    }
    private PedidoResponse toPedidoResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getEndereco().getId(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                new ArrayList<>()
        );
    }
}
