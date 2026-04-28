package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.EntregadorRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EntregadorResponse;
import br.com.centrallanches.lanchonete_api.entity.Entregador;
import br.com.centrallanches.lanchonete_api.repository.EntregadorRepository;
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

        return new EntregadorResponse(entregadorSalvo.getId(), entregadorSalvo.getNome(), entregadorSalvo.getCelular());
    }

    public EntregadorResponse findById(Integer id) {
        Entregador entregadorExist = entregadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O Entregador selecionado: " + id + " não foi encontrado"));

        return new EntregadorResponse(entregadorExist.getId(), entregadorExist.getNome(), entregadorExist.getCelular());
    }

    public List<EntregadorResponse> findAll() {
        return entregadorRepository.findAll()
                .stream()
                .map(entregador -> new EntregadorResponse(
                        entregador.getId(),
                        entregador.getNome(),
                        entregador.getCelular()))
                .collect(Collectors.toList());
    }

    public EntregadorResponse update(EntregadorRequest entregadorRequest, Integer id) {
        Entregador entregadorExist = entregadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível atualizar o entregador com ID: " + id));

        entregadorExist.setNome(entregadorRequest.nome());
        entregadorExist.setCelular(entregadorRequest.celular());

        Entregador entregadorUpdated = entregadorRepository.save(entregadorExist);

        return new EntregadorResponse(entregadorUpdated.getId(), entregadorUpdated.getNome(), entregadorUpdated.getCelular());
    }

    public void delete(Integer id) {
        if (!entregadorRepository.existsById(id)) {
            throw new RuntimeException("Entregador com ID " + id + " não encontrado para exclusão");
        }
        entregadorRepository.deleteById(id);
    }
}


