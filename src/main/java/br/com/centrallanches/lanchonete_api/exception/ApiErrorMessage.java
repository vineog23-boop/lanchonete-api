package br.com.centrallanches.lanchonete_api.exception;

import java.time.LocalDateTime;

public record ApiErrorMessage(
        String mensagem,
        Integer status,
        LocalDateTime dataHora
) {}
