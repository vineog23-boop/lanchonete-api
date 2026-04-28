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

    public ClienteResponse create (ClienteRequest clienteRequest) {

        Cliente clienteEntity = new Cliente();
        clienteEntity.setNome(clienteRequest.nome());
        clienteEntity.setCelular(clienteRequest.celular());
        clienteEntity.setEmail(clienteRequest.email());
        clienteEntity.setDataNascimento(clienteRequest.dataNascimento());
        ;

        Cliente clienteSalvo = clienteRepository.save(clienteEntity);

        return new ClienteResponse(clienteSalvo.getId(), clienteSalvo.getNome(), clienteSalvo.getCelular(), clienteSalvo.getEmail(), clienteSalvo.getDataNascimento());
    }


    public ClienteResponse findByID(UUID id) {
       Cliente clienteExist = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrado"));
       return new ClienteResponse(clienteExist.getId(), clienteExist.getNome(), clienteExist.getCelular(), clienteExist.getEmail(), clienteExist.getDataNascimento());


    }

    public List<ClienteResponse>findAll() {                                        //Método READ todos
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> new ClienteResponse(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCelular(),
                        cliente.getEmail(),
                        cliente.getDataNascimento()))
                .collect(Collectors.toList());
    }

    public ClienteResponse update( UUID id, ClienteRequest cliente) {
        Cliente clienteUpdate = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível atualizar o cliente com ID: " + id));
    clienteUpdate.setNome(cliente.nome());
    clienteUpdate.setCelular(cliente.celular());
    clienteUpdate.setEmail(cliente.email());
    clienteUpdate.setDataNascimento(cliente.dataNascimento());

    return new ClienteResponse(
            clienteUpdate.getId(),
            clienteUpdate.getNome(),
            clienteUpdate.getCelular(),
            clienteUpdate.getEmail(),
            clienteUpdate.getDataNascimento());
    }

    public void delete(UUID id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente com ID " + id + " não encontrado para exclusão");
        }
        clienteRepository.deleteById(id);
    }


    }



