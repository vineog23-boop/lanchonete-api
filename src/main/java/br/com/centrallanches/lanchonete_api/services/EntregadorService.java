package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.EntregadorRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EntregadorResponse;
import br.com.centrallanches.lanchonete_api.entity.Entregador;
import br.com.centrallanches.lanchonete_api.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    public EntregadorResponse save (EntregadorRequest entregadorRequest) {
                                                                                // MÉTODO CREATE
        Entregador entregador = new Entregador();
        entregador.setNome(entregadorRequest.nome());
        entregador.setCelular(entregadorRequest.celular());

        Entregador salvo = entregadorRepository.save(entregador);

        return new EntregadorResponse (salvo.getId(), salvo.getNome(), salvo.getCelular());
    }


    public Entregador findById(Integer id) {
        return entregadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("O Entregador selecionado: " + ( id ) + " não foi encontrado"));
    }

    public List<Entregador> findAll() {
        return entregadorRepository.findAll();
    }

    public Entregador update(Entregador entregador) {
        return entregadorRepository.save(entregador);
    }

    public void delete(Integer id) {
      entregadorRepository.deleteById(id);
    }
}


