package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.ProdutoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ProdutoResponse;
import br.com.centrallanches.lanchonete_api.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para operações relacionadas a produtos.
 */
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    /**
     * Cria um novo produto.
     * @param produtoRequest Dados do produto.
     * @return ProdutoResponse criado.
     */
    @PostMapping
    public ResponseEntity<ProdutoResponse> createProduct(@RequestBody ProdutoRequest produtoRequest) {
        ProdutoResponse produtoResponse = produtoService.save(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    /**
     * Lista todos os produtos.
     * @return Lista de ProdutoResponse.
     */
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAllProducts() {
        List<ProdutoResponse> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    /**
     * Busca um produto por ID.
     * @param id ID do produto.
     * @return ProdutoResponse correspondente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getProductById(@PathVariable Integer id) {
        ProdutoResponse produtoResponse = produtoService.findById(id);
        return ResponseEntity.ok(produtoResponse);
    }

    /**
     * Atualiza um produto.
     * @param id ID do produto.
     * @param produtoRequest Dados do produto.
     * @return ProdutoResponse atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> updateProduct(@PathVariable Integer id, @RequestBody ProdutoRequest produtoRequest) {
        ProdutoResponse produtoResponse = produtoService.update(produtoRequest, id);
        return ResponseEntity.ok(produtoResponse);
    }

    /**
     * Deleta um produto.
     * @param id ID do produto.
     * @return Resposta sem conteúdo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
