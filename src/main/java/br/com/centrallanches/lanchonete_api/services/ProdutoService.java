package br.com.centrallanches.lanchonete_api.services;

import br.com.centrallanches.lanchonete_api.dto.request.ProdutoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ProdutoResponse;
import br.com.centrallanches.lanchonete_api.entity.Produto;
import br.com.centrallanches.lanchonete_api.repository.ProdutoRepository;
import br.com.centrallanches.lanchonete_api.exception.ResourceNotFoundException; // Import adicionado
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

        return toProdutoResponse(savedEntity);
    }

    public ProdutoResponse findById(Integer id) {
        Produto entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + id + " não encontrado")); // Alterado

        return toProdutoResponse(entity);
    }

    public List<ProdutoResponse> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toProdutoResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponse update(ProdutoRequest request, Integer id) {
        Produto entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID: " + id + " não encontrado para atualização")); // Alterado

        entity.setNome(request.nome());
        entity.setPreco(request.preco());
        entity.setCategoria(request.categoria());

        Produto savedEntity = produtoRepository.save(entity);

        return toProdutoResponse(savedEntity);
    }

    public void delete(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto com ID " + id + " não encontrado para exclusão"); // Alterado
        }
        produtoRepository.deleteById(id);
    }
    private ProdutoResponse toProdutoResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria());
    }
}
