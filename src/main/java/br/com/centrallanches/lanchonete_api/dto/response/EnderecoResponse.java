package br.com.centrallanches.lanchonete_api.dto.response;


import java.util.UUID;

public record EnderecoResponse(
        UUID id,
        String logradouro,
        String numero,
        String cidade,
        String estado,
        String cep,
        String complemento
)
{}
