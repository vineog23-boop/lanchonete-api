package br.com.centrallanches.lanchonete_api.dto.request;

public record ItemPedidoRequest(
        Integer produtoId,
        Integer quantidade
)
{}