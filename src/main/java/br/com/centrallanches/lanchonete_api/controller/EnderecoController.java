package br.com.centrallanches.lanchonete_api.controller;

import br.com.centrallanches.lanchonete_api.dto.request.EnderecoRequest;
import br.com.centrallanches.lanchonete_api.dto.response.EnderecoResponse;
import br.com.centrallanches.lanchonete_api.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Tag(name = "Endereços" , description = "Endereços de entrega dos clientes")
@RequestMapping("/enderecos")
@RestController
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/{id}")
    @Operation(summary = "Busca pelo endereço do cliente" , description = "busca pelo endereço corresponde ao id do cliente")
    @ApiResponse(responseCode = "200" , description = "Endereço encontrado com sucesso")
    @ApiResponse(responseCode = "404" , description = "Endereço não encontrado")

    public ResponseEntity<EnderecoResponse> findById(@PathVariable UUID id){
        EnderecoResponse enderecoExist = enderecoService.findById(id);
        return ResponseEntity.ok(enderecoExist);
    }

    @GetMapping()
    @Operation(summary = "Busca por todos os endereços listados" , description = "Faz uma busca de todos os endereços listados no sistema")
    @ApiResponse(responseCode = "200", description = "Lista de endereços encontrada com sucesso")

    public ResponseEntity<List<EnderecoResponse>> findAll(){
        List<EnderecoResponse> enderecoList = enderecoService.findAll();
        return ResponseEntity.ok(enderecoList);
    }

    @PostMapping()
    @Operation(summary = "Cria um novo endereço" , description = "Cria um novo endereço para um cliente")
    @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")

    public ResponseEntity<EnderecoResponse>save(@Valid @RequestBody EnderecoRequest enderecoRequest){
        EnderecoResponse enderecoCriado = enderecoService.save(enderecoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCriado);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço existente" , description = "Atualiza os dados de um endereço baseado no seu ID")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado para atualização")

    public ResponseEntity<EnderecoResponse> update(@PathVariable UUID id, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        EnderecoResponse enderecoUpdate = enderecoService.update(enderecoRequest,id );
        return ResponseEntity.ok(enderecoUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um endereço", description = "Remove um endereço do sistema baseado no seu ID")
    @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado para exclusão")

    public ResponseEntity<Void> delete(@PathVariable UUID id){
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
