package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.PedidoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.PedidoResponse;
import br.com.centrallanches.lanchonete_api.services.PedidoService;
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

@Tag(name = "Pedidos", description = "Endpoints relacionados aos pedidos de clientes")
@RequiredArgsConstructor
@RequestMapping("/pedido")
@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping()
    @Operation(summary = "Cria um novo pedido", description = "Registra um novo pedido no sistema")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")

    public ResponseEntity <PedidoResponse>  create(@Valid @RequestBody PedidoRequest pedidoRequest){
        PedidoResponse pedidoCriado = pedidoService.create(pedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um pedido por ID", description = "Retorna um pedido específico baseado no seu ID")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")

    public ResponseEntity <PedidoResponse>findById (@PathVariable UUID id){
        PedidoResponse pedidoEncontrado = pedidoService.findById(id);
        return ResponseEntity.ok(pedidoEncontrado);
    }

    @GetMapping()
    @Operation(summary = "Lista todos os pedidos", description = "Retorna uma lista de todos os pedidos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso")

    public ResponseEntity<List<PedidoResponse>>findAll(){
        List<PedidoResponse> pedidoList = pedidoService.findAll();
        return ResponseEntity.ok(pedidoList);

    }

    @PutMapping ("/{id}")
    @Operation(summary = "Atualiza um pedido existente", description = "Atualiza os dados de um pedido baseado no seu ID")
    @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado para atualização")

    public ResponseEntity<PedidoResponse> update(@PathVariable UUID id, @Valid @RequestBody PedidoRequest pedidoRequest){
        PedidoResponse pedidoAtualizado = pedidoService.update(id, pedidoRequest);
        return ResponseEntity.ok(pedidoAtualizado);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pedido", description = "Remove um pedido do sistema baseado no seu ID")
    @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado para exclusão")

    public ResponseEntity<Void>delete(@PathVariable UUID id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
