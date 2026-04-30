package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.ClienteRequest;
import br.com.centrallanches.lanchonete_api.dto.response.ClienteResponse;
import br.com.centrallanches.lanchonete_api.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteCreated = clienteService.create(clienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable UUID id) {
        ClienteResponse clienteEncontrado = clienteService.findByID(id);
        return ResponseEntity.ok(clienteEncontrado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> findAll() {
        List<ClienteResponse> clienteList = clienteService.findAll();
        return ResponseEntity.ok(clienteList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@PathVariable UUID id, @Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteUpdated = clienteService.update(id, clienteRequest);
        return ResponseEntity.ok(clienteUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
