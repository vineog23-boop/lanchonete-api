package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.ProdutoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ProdutoResponse;
import br.com.centrallanches.lanchonete_api.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "Endpoints relacionados aos produtos da lanchonete")
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Cria um novo produto", description = "Registra um novo produto no sistema")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")

    public ResponseEntity<ProdutoResponse> save(@Valid @RequestBody ProdutoRequest produtoRequest) {
        ProdutoResponse produtoCriado = produtoService.save(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista de todos os produtos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")

    public ResponseEntity<List<ProdutoResponse>> findAll() {
        List<ProdutoResponse> listaProdutos = produtoService.findAll();
        return ResponseEntity.ok(listaProdutos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto por ID", description = "Retorna um produto específico baseado no seu ID")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")

    public ResponseEntity<ProdutoResponse> findById(@PathVariable Integer id) {
        ProdutoResponse produtoEncontrado = produtoService.findById(id);
        return ResponseEntity.ok(produtoEncontrado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto existente", description = "Atualiza os dados de um produto baseado no seu ID")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado para atualização")

    public ResponseEntity<ProdutoResponse> update(@PathVariable Integer id, @Valid @RequestBody ProdutoRequest produtoRequest) {
        ProdutoResponse produtoUpdate = produtoService.update(produtoRequest, id);
        return ResponseEntity.ok(produtoUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto", description = "Remove um produto do sistema baseado no seu ID")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado para exclusão")

    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
