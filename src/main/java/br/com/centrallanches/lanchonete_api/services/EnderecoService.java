package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.EnderecoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EnderecoResponse;
import br.com.centrallanches.lanchonete_api.entity.Cliente;
import br.com.centrallanches.lanchonete_api.entity.Endereco;
import br.com.centrallanches.lanchonete_api.repository.ClienteRepository;
import br.com.centrallanches.lanchonete_api.repository.EnderecoRepository;
import br.com.centrallanches.lanchonete_api.exception.ResourceNotFoundException; 
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoResponse save(EnderecoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Endereco entity = new Endereco();
        entity.setCliente(cliente);
        entity.setLogradouro(request.logradouro());
        entity.setNumero(request.numero());
        entity.setCep(request.cep());
        entity.setBairro(request.bairro());
        entity.setCidade(request.cidade());
        entity.setEstado(request.estado());
        entity.setComplemento(request.complemento());

        Endereco savedEntity = enderecoRepository.save(entity);

        return  toEnderecoResponse(savedEntity);

    }


    public EnderecoResponse findById(UUID id) {
        Endereco entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço com ID: " + id + " não encontrado"));

        return toEnderecoResponse(entity);
    }

    public List<EnderecoResponse> findAll() {
        return enderecoRepository.findAll()
                .stream()
                .map(this::toEnderecoResponse)
                .toList();
    }

    public EnderecoResponse update(EnderecoRequest request, UUID id) {
        Endereco entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar o endereço com ID: " + id));

        entity.setLogradouro(request.logradouro());
        entity.setNumero(request.numero());
        entity.setCep(request.cep());
        entity.setBairro(request.bairro());
        entity.setCidade(request.cidade());
        entity.setEstado(request.estado());
        entity.setComplemento(request.complemento());

        Endereco savedEntity = enderecoRepository.save(entity);

        return toEnderecoResponse(savedEntity);
    }

    public void delete(UUID id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Endereço com ID " + id + " não encontrado para exclusão");
        }
        enderecoRepository.deleteById(id);

    }

    private EnderecoResponse toEnderecoResponse(Endereco endereco) {
        return new EnderecoResponse(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getComplemento() );
    }
}


