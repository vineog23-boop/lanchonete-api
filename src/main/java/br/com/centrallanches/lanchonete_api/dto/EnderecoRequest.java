package br.com.centrallanches.lanchonete_api.dto;

import java.util.UUID;

public record EnderecoRequest(
        UUID clienteId,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String complemento
)
{}
