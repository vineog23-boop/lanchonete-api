package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.EnderecoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EnderecoResponse;
import br.com.centrallanches.lanchonete_api.entity.Endereco;
import br.com.centrallanches.lanchonete_api.repository.EnderecoRepository;
import br.com.centrallanches.lanchonete_api.exception.ResourceNotFoundException; // Import adicionado
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoResponse save(EnderecoRequest request) {
        Endereco entity = new Endereco();
        entity.setLogradouro(request.logradouro());
        entity.setNumero(request.numero());
        entity.setCep(request.cep());
        entity.setBairro(request.bairro());
        entity.setCidade(request.cidade());
        entity.setEstado(request.estado());
        entity.setComplemento(request.complemento());

        Endereco savedEntity = enderecoRepository.save(entity);

        return new EnderecoResponse(savedEntity.getId(), savedEntity.getLogradouro(), savedEntity.getNumero(), savedEntity.getCep(), savedEntity.getBairro(), savedEntity.getCidade(), savedEntity.getEstado());
    }

    public EnderecoResponse findById(UUID id) {
        Endereco entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço com ID: " + id + " não encontrado")); // Alterado

        return new EnderecoResponse(entity.getId(), entity.getLogradouro(), entity.getNumero(), entity.getCep(), entity.getBairro(), entity.getCidade(), entity.getEstado());
    }

    public List<EnderecoResponse> findAll() {
        return enderecoRepository.findAll()
                .stream()
                .map(entity -> new EnderecoResponse(entity.getId(), entity.getLogradouro(), entity.getNumero(), entity.getCep(), entity.getBairro(), entity.getCidade(), entity.getEstado()))
                .toList();
    }

    public EnderecoResponse update(EnderecoRequest request, UUID id) {
        Endereco entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar o endereço com ID: " + id)); // Alterado

        entity.setLogradouro(request.logradouro());
        entity.setNumero(request.numero());
        entity.setCep(request.cep());
        entity.setBairro(request.bairro());
        entity.setCidade(request.cidade());
        entity.setEstado(request.estado());
        entity.setComplemento(request.complemento());

        Endereco savedEntity = enderecoRepository.save(entity);

        return new EnderecoResponse(savedEntity.getId(), savedEntity.getLogradouro(), savedEntity.getNumero(), savedEntity.getCep(), savedEntity.getBairro(), savedEntity.getCidade(), savedEntity.getEstado());
    }

    public void delete(UUID id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Endereço com ID " + id + " não encontrado para exclusão"); // Alterado
        }
        enderecoRepository.deleteById(id);
    }
}
