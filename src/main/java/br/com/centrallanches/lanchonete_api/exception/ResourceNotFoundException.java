package br.com.centrallanches.lanchonete_api.exception;

// Esta exceção é para quando um recurso (Produto, Cliente, etc.) não é encontrado
// Estende RuntimeException para não precisar declarar 'throws' nos métodos
public class ResourceNotFoundException extends RuntimeException {

    // Construtor que recebe uma mensagem, que será a mensagem do erro HTTP
    public ResourceNotFoundException(String message) {
        super(message); // Passa a mensagem para o construtor da classe pai (RuntimeException)
    }
}
