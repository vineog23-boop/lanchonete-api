package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.entity.Endereco;
import br.com.centrallanches.lanchonete_api.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service

public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public Endereco save ( Endereco endereco) {                     // MÉTODO CREATE
        return enderecoRepository.save(endereco);
    }


    public Endereco findById(UUID id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O endereço enviado : " + ( id ) + " não foi encontrado"));
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco update(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void delete(UUID id) {
        enderecoRepository.deleteById(id);
    }
}
