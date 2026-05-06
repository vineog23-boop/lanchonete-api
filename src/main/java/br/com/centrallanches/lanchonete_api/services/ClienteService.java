package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.ClienteRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ClienteResponse;
import br.com.centrallanches.lanchonete_api.entity.Cliente;
import br.com.centrallanches.lanchonete_api.repository.ClienteRepository;
import br.com.centrallanches.lanchonete_api.exception.ResourceNotFoundException; // Import adicionado
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteResponse create(ClienteRequest request) {
        Cliente entity = new Cliente();
        entity.setNome(request.nome());
        entity.setCelular(request.celular());
        entity.setEmail(request.email());
        entity.setDataNascimento(request.dataNascimento());

        Cliente savedEntity = clienteRepository.save(entity);

        return toClienteResponse(savedEntity);
    }

    public ClienteResponse findByID(UUID id) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado")); // Alterado
        return toClienteResponse(entity);
    }

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toClienteResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse update(UUID id, ClienteRequest request) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar o cliente com ID: " + id)); // Alterado

        entity.setNome(request.nome());
        entity.setCelular(request.celular());
        entity.setEmail(request.email());
        entity.setDataNascimento(request.dataNascimento());

        Cliente savedEntity = clienteRepository.save(entity);

        return toClienteResponse(savedEntity);
    }

    public void delete(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente com ID " + id + " não encontrado para exclusão"); // Alterado
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponse toClienteResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCelular(),
                cliente.getEmail(),
                cliente.getDataNascimento());
    }
}
