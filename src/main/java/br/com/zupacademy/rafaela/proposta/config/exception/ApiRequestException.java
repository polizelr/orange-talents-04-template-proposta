package br.com.zupacademy.rafaela.proposta.config.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
    private final String field;

    public ApiRequestException(String message, HttpStatus httpStatus, String field) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        this.field = field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
