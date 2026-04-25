package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.entity.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @PostMapping

    public ResponseEntity<Cliente> save(Cliente cliente) {
        return ResponseEntity.ok().body(cliente);
    }


}