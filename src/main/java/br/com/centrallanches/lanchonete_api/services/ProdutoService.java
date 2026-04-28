package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.ProdutoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ProdutoResponse;
import br.com.centrallanches.lanchonete_api.entity.Produto;
import br.com.centrallanches.lanchonete_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponse save(ProdutoRequest request) {
        Produto entity = new Produto();
        entity.setNome(request.nome());
        entity.setPreco(request.preco());
        entity.setCategoria(request.categoria());

        Produto savedEntity = produtoRepository.save(entity);

        return new ProdutoResponse(savedEntity.getId(), savedEntity.getNome(), savedEntity.getPreco(), savedEntity.getCategoria());
    }

    public ProdutoResponse findById(Integer id) {
        Produto entity = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com ID: " + id + " não encontrado"));

        return new ProdutoResponse(entity.getId(), entity.getNome(), entity.getPreco(), entity.getCategoria());
    }

    public List<ProdutoResponse> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(entity -> new ProdutoResponse(entity.getId(), entity.getNome(), entity.getPreco(), entity.getCategoria()))
                .collect(Collectors.toList());
    }

    public ProdutoResponse update(ProdutoRequest request, Integer id) {
        Produto entity = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com ID: " + id + " não encontrado para atualização"));

        entity.setNome(request.nome());
        entity.setPreco(request.preco());
        entity.setCategoria(request.categoria());

        Produto savedEntity = produtoRepository.save(entity);

        return new ProdutoResponse(savedEntity.getId(), savedEntity.getNome(), savedEntity.getPreco(), savedEntity.getCategoria());
    }

    public void delete(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto com ID " + id + " não encontrado para exclusão");
        }
        produtoRepository.deleteById(id);
    }
}
