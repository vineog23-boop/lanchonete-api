package br.com.centrallanches.lanchonete_api.dto.request;

import br.com.centrallanches.lanchonete_api.entity.enums.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "O nome do produto é obrigatório")
        String nome,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "A categoria é obrigatória")
        CategoriaProduto categoria
)
{}
