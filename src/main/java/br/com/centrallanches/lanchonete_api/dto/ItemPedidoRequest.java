package br.com.centrallanches.lanchonete_api.dto;

public record ItemPedidoRequest(
        Integer produtoId,
        Integer quantidade
)
{}