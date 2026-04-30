package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.ProdutoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ProdutoResponse;
import br.com.centrallanches.lanchonete_api.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> save(@Valid @RequestBody ProdutoRequest produtoRequest) {
        ProdutoResponse produtoCriado = produtoService.save(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> findAll() {
        List<ProdutoResponse> listaProdutos = produtoService.findAll();
        return ResponseEntity.ok(listaProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Integer id) {
        ProdutoResponse produtoEncontrado = produtoService.findById(id);
        return ResponseEntity.ok(produtoEncontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@PathVariable Integer id, @Valid @RequestBody ProdutoRequest produtoRequest) {
        ProdutoResponse produtoUpdate = produtoService.update(produtoRequest, id);
        return ResponseEntity.ok(produtoUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
