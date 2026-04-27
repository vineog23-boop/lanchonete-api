package br.com.centrallanches.lanchonete_api.dto.request;

import java.time.LocalDate;

public record ClienteRequest(
    String nome,
    String celular,
    String email,
    LocalDate dataNascimento
    )

{}
