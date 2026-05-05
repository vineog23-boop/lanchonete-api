package br.com.centrallanches.lanchonete_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

//Anotacao principal : define que esssa classe vai centralizar o tratamento de excessoes s
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 0. Metodo auxiliar: sistematiza  um padrao de resposta para erros
    private ResponseEntity<ErroPadrao> buildResponse(String message, HttpStatus status) {
        ErroPadrao erro = new ErroPadrao(message, status.value(), LocalDateTime.now());
        return ResponseEntity.status(status).body(erro);
    }

    // 1. Tratamento específico para ResourceNotFoundException (Status 404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroPadrao> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 2. Tratamento para RuntimeExceptions genéricas (Status 500)
    // Captura RuntimeExceptions que não foram tratadas por handlers mais específicos.
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroPadrao> handleRuntimeException(RuntimeException ex) {
        // Por segurança, não expomos a mensagem da exceção para o cliente em erros 500 inesperados
        // Mas para fins de debug e estudo, vamos mostrar por enquanto, mas em produção isso seria mais genérico.
        return buildResponse("Ocorreu um erro inesperado no servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 3. Tratamento genérico para qualquer outra Exception (Status 500)
    // Este é o "catch-all" para todas as outras exceções não tratadas acima.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> handleGenericException(Exception ex) {
        return buildResponse("Ocorreu um erro interno inesperado. Tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
