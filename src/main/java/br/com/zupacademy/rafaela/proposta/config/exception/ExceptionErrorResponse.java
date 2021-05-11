package br.com.zupacademy.rafaela.proposta.config.exception;

import java.util.ArrayList;
import java.util.List;

public class ExceptionErrorResponse {
    private List<ErrorResponse> errors = new ArrayList<>();

    public void addErrors(String field, String message) {
        ErrorResponse error = new ErrorResponse(field, message);
        errors.add(error);
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }
}
