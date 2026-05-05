package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.ClienteRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ClienteResponse;
import br.com.centrallanches.lanchonete_api.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor

@Tag(name = "Cliente", description = "Endpoints relacionados aos clientes da lanchonete")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;



    @PostMapping
    @Operation(summary = "Cria um novo cliente" ,description = "Cria um novo cliente no sistema")
    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados invalidos fornecidos")

    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteCreated = clienteService.create(clienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreated);
    }


   @GetMapping("/{id}")
   @Operation(summary = "Busca um cliente pelo ID" ,description = "Retorna um cliente especifico pelo ID")
   @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso")
   @ApiResponse (responseCode = "404", description = "Cliente não encontrado")

    public ResponseEntity<ClienteResponse> findById(@PathVariable UUID id) {
        ClienteResponse clienteEncontrado = clienteService.findByID(id);
        return ResponseEntity.ok(clienteEncontrado);
    }


  @GetMapping
  @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados")
  @ApiResponse(responseCode = "200", description = "Lista de clientes encontrada com sucesso")
  @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado")

    public ResponseEntity<List<ClienteResponse>> findAll() {
        List<ClienteResponse> clienteList = clienteService.findAll();
        return ResponseEntity.ok(clienteList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um cliente existente pelo ID", description = "Atualiza os dados de um cliente existente pelo ID")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso")
    @ApiResponse(responseCode = "400",description = "Dados invalidos fornecidos")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado para a atualizacão")

    public ResponseEntity<ClienteResponse> update(@PathVariable UUID id, @Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteUpdated = clienteService.update(id, clienteRequest);
        return ResponseEntity.ok(clienteUpdated);
    }



   @DeleteMapping("/{id}")
   @Operation (summary = "Deleta um cliente pelo ID", description = "Deleta um cliente pelo ID")
   @ApiResponse( responseCode = "204", description = "Cliente deletado com sucesso")
   @ApiResponse( responseCode = "404", description = "Cliente não encontrado para exclusão")

    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
