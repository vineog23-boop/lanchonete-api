package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.entity.Cliente;
import br.com.centrallanches.lanchonete_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente create (Cliente cliente) {                                 // Método CREATE
        return clienteRepository.save(cliente);
    }


    public Cliente buscarPorId(UUID id) {                                   //Método READ ID
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrado"));
    }

    public List<Cliente> buscarTodos() {                                        //Método READ todos
        return clienteRepository.findAll();
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);                                     // MÉTODO UPDATE
    }

    public void delete(UUID id) {
        clienteRepository.deleteById(id);

    }


    }



