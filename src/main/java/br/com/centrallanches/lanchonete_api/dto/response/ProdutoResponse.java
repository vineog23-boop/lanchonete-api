package br.com.centrallanches.lanchonete_api.dto.response;

import br.com.centrallanches.lanchonete_api.entity.enums.CategoriaProduto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Integer id,
        String nome,
        BigDecimal preco,
        CategoriaProduto categoria
)
{}
