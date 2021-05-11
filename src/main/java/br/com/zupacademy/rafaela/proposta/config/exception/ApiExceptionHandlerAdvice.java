package br.com.zupacademy.rafaela.proposta.config.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice {
    private MessageSource messageSource;

    public ApiExceptionHandlerAdvice(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ExceptionErrorResponse> handle(ApiRequestException exception){
        ExceptionErrorResponse errorResponse = new ExceptionErrorResponse();
        errorResponse.addErrors(exception.getField(), exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionErrorResponse> handle(MethodArgumentNotValidException exception){
        ExceptionErrorResponse errorResponse = new ExceptionErrorResponse();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            errorResponse.addErrors(error.getField(), errorMessage);
        });

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
