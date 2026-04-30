package br.com.centrallanches.lanchonete_api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record PedidoRequest(
        @NotNull(message = "O ID do cliente é obrigatório")
        UUID clienteId,

        @NotNull(message = "O ID do endereço é obrigatório")
        UUID enderecoId,

        @NotEmpty(message = "O pedido deve ter pelo menos um item")
        @Valid
        List<ItemPedidoRequest> itens
)
{}
