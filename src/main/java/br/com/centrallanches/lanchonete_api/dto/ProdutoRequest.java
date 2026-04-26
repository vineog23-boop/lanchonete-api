package br.com.centrallanches.lanchonete_api.dto;

import br.com.centrallanches.lanchonete_api.entity.enums.CategoriaProduto;

import java.math.BigDecimal;

public record ProdutoRequest(
        String nome,
        BigDecimal preco,
        CategoriaProduto categoria
)
{}
