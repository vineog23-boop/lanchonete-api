package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.EntregadorRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EntregadorResponse;
import br.com.centrallanches.lanchonete_api.services.EntregadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    @PostMapping
    public ResponseEntity<EntregadorResponse> save(@Valid @RequestBody EntregadorRequest entregadorRequest){
        EntregadorResponse entregadorCriado = entregadorService.save(entregadorRequest);

        return ResponseEntity.status(201).body(entregadorCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregadorResponse> findById(@PathVariable Integer id){
        EntregadorResponse entregadorEncontrado = entregadorService.findById(id);
        return ResponseEntity.ok(entregadorEncontrado);
    }

    @GetMapping
    public ResponseEntity <List<EntregadorResponse>>findAll(){
        List<EntregadorResponse> entregadorList = entregadorService.findAll();
        return ResponseEntity.ok(entregadorList);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EntregadorResponse> update(@PathVariable Integer id, @Valid @RequestBody EntregadorRequest entregadorRequest){
        EntregadorResponse entregadorAtualizado = entregadorService.update(entregadorRequest, id);
        return ResponseEntity.ok(entregadorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        entregadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
