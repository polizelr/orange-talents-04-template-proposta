package br.com.zupacademy.rafaela.proposta.config.exception;

public class ErrorResponse {
    private String field;
    private String message;

    public ErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
