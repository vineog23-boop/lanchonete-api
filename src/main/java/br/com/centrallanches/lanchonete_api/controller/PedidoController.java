package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.PedidoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.PedidoResponse;
import br.com.centrallanches.lanchonete_api.services.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/pedido")
@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity <PedidoResponse>  create(@Valid @RequestBody PedidoRequest pedidoRequest){
        PedidoResponse pedidoCriado = pedidoService.create(pedidoRequest);
        return ResponseEntity.status(201).body(pedidoCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity <PedidoResponse>findById (@PathVariable UUID id){
        PedidoResponse pedidoEncontrado = pedidoService.findById(id);
        return ResponseEntity.ok(pedidoEncontrado);
    }

    @GetMapping()
    public ResponseEntity<List<PedidoResponse>>findAll(){
        List<PedidoResponse> pedidoList = pedidoService.findAll();
        return ResponseEntity.ok(pedidoList);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<PedidoResponse> update(@PathVariable UUID id, @Valid @RequestBody PedidoRequest pedidoRequest){
        PedidoResponse pedidoAtualizad = pedidoService.update(id, pedidoRequest);
        return ResponseEntity.ok(pedidoAtualizad);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable UUID id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
