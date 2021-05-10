package br.com.zupacademy.rafaela.proposta.config.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public void addError(String message){
        globalErrorMessages.add(message);
    }

    public void addFieldError (String field, String message){
        FieldErrorDTO fieldError = new FieldErrorDTO(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages(){
        return globalErrorMessages;
    }

    public List<FieldErrorDTO> getErrors(){
        return fieldErrors;
    }
}
