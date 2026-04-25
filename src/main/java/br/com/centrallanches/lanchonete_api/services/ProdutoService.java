package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.entity.Produto;
import br.com.centrallanches.lanchonete_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto save (Produto produto) {                     // MÉTODO CREATE
        return produtoRepository.save(produto);
    }


    public Produto findById(Integer  id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O produto selecionado : " + ( id ) + " não foi encontrado"));
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto update(Produto endereco) {
        return produtoRepository.save(endereco);
    }

    public void delete(Integer id) {
        produtoRepository.deleteById(id);
    }
}


