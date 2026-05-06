package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.EntregadorRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EntregadorResponse;
import br.com.centrallanches.lanchonete_api.entity.Entregador;
import br.com.centrallanches.lanchonete_api.repository.EntregadorRepository;
import br.com.centrallanches.lanchonete_api.exception.ResourceNotFoundException; // Import adicionado
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    public EntregadorResponse save(EntregadorRequest entregadorRequest) {

        Entregador entregadorEntity = new Entregador();
        entregadorEntity.setNome(entregadorRequest.nome());
        entregadorEntity.setCelular(entregadorRequest.celular());

        Entregador entregadorSalvo = entregadorRepository.save(entregadorEntity);

        return toEntregadorResponse(entregadorSalvo);
    }

    public EntregadorResponse findById(Integer id) {
        Entregador entregadorExist = entregadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O Entregador selecionado: " + id + " não foi encontrado")); // Alterado

        return toEntregadorResponse(entregadorExist);
    }

    public List<EntregadorResponse> findAll() {
        return entregadorRepository.findAll()
                .stream()
                .map(this::toEntregadorResponse)
                .collect(Collectors.toList());
    }

    public EntregadorResponse update(EntregadorRequest entregadorRequest, Integer id) {
        Entregador entregadorExist = entregadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar o entregador com ID: " + id)); // Alterado

        entregadorExist.setNome(entregadorRequest.nome());
        entregadorExist.setCelular(entregadorRequest.celular());

        Entregador entregadorUpdated = entregadorRepository.save(entregadorExist);

        return toEntregadorResponse(entregadorUpdated);
    }

    public void delete(Integer id) {
        if (!entregadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entregador com ID " + id + " não encontrado para exclusão"); // Alterado
        }
        entregadorRepository.deleteById(id);
    }

    private EntregadorResponse toEntregadorResponse(Entregador entregador) {
        return new EntregadorResponse(
                entregador.getId(),
                entregador.getNome(),
                entregador.getCelular());
    }
}
