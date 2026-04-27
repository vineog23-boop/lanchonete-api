package br.com.centrallanches.lanchonete_api.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record  ClienteResponse(
        UUID id,
        String nome,
        String celular,
        String email,
        LocalDate dataNascimento
)

{}
