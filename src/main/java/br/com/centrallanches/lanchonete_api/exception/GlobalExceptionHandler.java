package br.com.centrallanches.lanchonete_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Tratamento específico para erros de "não encontrado" (Status 404)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroPadrao> handleNotFound(RuntimeException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 2. Tratamento genérico para erros inesperados do servidor (Status 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> handleGeneric(Exception ex) {
        return buildResponse("Ocorreu um erro interno inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // MÉTODO AUXILIAR: Centraliza a criação do objeto de erro e evita repetição
    private ResponseEntity<ErroPadrao> buildResponse(String message, HttpStatus status) {
        ErroPadrao erro = new ErroPadrao(message, status.value(), LocalDateTime.now());
        return ResponseEntity.status(status).body(erro);
    }
}
