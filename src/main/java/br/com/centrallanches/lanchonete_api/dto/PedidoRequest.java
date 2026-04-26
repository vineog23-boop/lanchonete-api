package br.com.centrallanches.lanchonete_api.dto;

import java.util.List;
import java.util.UUID;

public record PedidoRequest(
        UUID clienteId,
        UUID enderecoId,
        List<ItemPedidoRequest> itens
)
{}
