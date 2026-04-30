package br.com.centrallanches.lanchonete_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EntregadorRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O celular é obrigatório")
        String celular
)
{}
