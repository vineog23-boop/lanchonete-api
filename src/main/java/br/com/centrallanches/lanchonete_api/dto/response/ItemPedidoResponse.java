package br.com.centrallanches.lanchonete_api.dto.response;

import java.math.BigDecimal;

record ItemPedidoResponse(
        Integer id,
        Integer produtoId,
        Integer quantidade,
        BigDecimal precoUnitario
)
{}