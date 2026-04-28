package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.ClienteRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ClienteResponse;
import br.com.centrallanches.lanchonete_api.entity.Cliente;
import br.com.centrallanches.lanchonete_api.repository.ClienteRepository;
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

        return new ClienteResponse(savedEntity.getId(), savedEntity.getNome(), savedEntity.getCelular(), savedEntity.getEmail(), savedEntity.getDataNascimento());
    }

    public ClienteResponse findByID(UUID id) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrado"));
        return new ClienteResponse(entity.getId(), entity.getNome(), entity.getCelular(), entity.getEmail(), entity.getDataNascimento());
    }

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(entity -> new ClienteResponse(
                        entity.getId(),
                        entity.getNome(),
                        entity.getCelular(),
                        entity.getEmail(),
                        entity.getDataNascimento()))
                .collect(Collectors.toList());
    }

    public ClienteResponse update(UUID id, ClienteRequest request) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível atualizar o cliente com ID: " + id));

        entity.setNome(request.nome());
        entity.setCelular(request.celular());
        entity.setEmail(request.email());
        entity.setDataNascimento(request.dataNascimento());

        Cliente savedEntity = clienteRepository.save(entity);

        return new ClienteResponse(
                savedEntity.getId(),
                savedEntity.getNome(),
                savedEntity.getCelular(),
                savedEntity.getEmail(),
                savedEntity.getDataNascimento());
    }

    public void delete(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente com ID " + id + " não encontrado para exclusão");
        }
        clienteRepository.deleteById(id);
    }
}
