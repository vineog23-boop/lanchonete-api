package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.entity.Entregador;
import br.com.centrallanches.lanchonete_api.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    public Entregador save (Entregador entregador) {                     // MÉTODO CREATE
        return entregadorRepository.save(entregador);
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


