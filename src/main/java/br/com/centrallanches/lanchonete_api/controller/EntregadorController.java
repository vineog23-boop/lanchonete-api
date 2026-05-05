package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.EntregadorRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EntregadorResponse;
import br.com.centrallanches.lanchonete_api.services.EntregadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Entregadores", description = "Endpoints relacionados aos entregadores da lanchonete")
@RequiredArgsConstructor
@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    @PostMapping
    @Operation(summary = "Cria um entregador", description = "Cria um entregador com os dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Entregador criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")

    public ResponseEntity<EntregadorResponse> save(@Valid @RequestBody EntregadorRequest entregadorRequest){
        EntregadorResponse entregadorCriado = entregadorService.save(entregadorRequest);

        return ResponseEntity.status(201).body(entregadorCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um entregador pelo ID", description = "Busca um entregador pelo ID")
    @ApiResponse(responseCode = "200", description = "Entregador encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Entregador não encontrado")

    public ResponseEntity<EntregadorResponse> findById(@PathVariable Integer id){
        EntregadorResponse entregadorEncontrado = entregadorService.findById(id);
        return ResponseEntity.ok(entregadorEncontrado);
    }

    @GetMapping
    @Operation(summary = "Lista todos os entregadores", description = "Lista todos os entregadores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de entregadores encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Nenhum entregador encontrado")

    public ResponseEntity <List<EntregadorResponse>>findAll(){
        List<EntregadorResponse> entregadorList = entregadorService.findAll();
        return ResponseEntity.ok(entregadorList);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um entregador pelo ID", description = "Atualiza um entregador pelo ID com os dados fornecidos")
    @ApiResponse(responseCode = "200", description = "Entregador atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "404", description = "Entregador não encontrado para atualização")

    public ResponseEntity<EntregadorResponse> update(@PathVariable Integer id, @Valid @RequestBody EntregadorRequest entregadorRequest){
        EntregadorResponse entregadorAtualizado = entregadorService.update(entregadorRequest, id);
        return ResponseEntity.ok(entregadorAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um entregador pelo ID", description = "Deleta um entregador pelo ID")
    @ApiResponse(responseCode = "204", description = "Entregador deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Entregador não encontrado para exclusão")

    public ResponseEntity<Void> delete(@PathVariable Integer id){
        entregadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
