package br.com.centrallanches.lanchonete_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record ClienteRequest(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O celular é obrigatório")
    String celular,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    String email,

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    LocalDate dataNascimento
    )

{}
