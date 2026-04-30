package br.com.centrallanches.lanchonete_api.exception;

import java.time.LocalDateTime;

public record ErroPadrao(
        String mensagem,
        int status,
        LocalDateTime dataHora
) {}
