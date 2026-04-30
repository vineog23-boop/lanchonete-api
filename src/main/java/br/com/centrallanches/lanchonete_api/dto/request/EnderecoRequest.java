package br.com.centrallanches.lanchonete_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

public record EnderecoRequest(
        @NotNull(message = "O ID do cliente é obrigatório")
        UUID clienteId,

        @NotBlank(message = "O logradouro é obrigatório")
        String logradouro,

        @NotBlank(message = "O número é obrigatório")
        String numero,

        @NotBlank(message = "O bairro é obrigatório")
        String bairro,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        String estado,

        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String cep,

        String complemento
)
{}
