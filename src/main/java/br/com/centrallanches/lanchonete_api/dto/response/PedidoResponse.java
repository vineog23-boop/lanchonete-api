package br.com.centrallanches.lanchonete_api.dto.response;

import br.com.centrallanches.lanchonete_api.dto.request.ItemPedidoRequest;
import br.com.centrallanches.lanchonete_api.entity.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoResponse(
        UUID id,
        UUID clienteId,
        UUID enderecoId,
        StatusPedido status,
        BigDecimal valorTotal,
        List<ItemPedidoResponse> itens

) {
}
