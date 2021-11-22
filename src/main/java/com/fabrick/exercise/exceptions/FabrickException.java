package com.fabrick.exercise.exceptions;

import com.fabrick.exercise.models.generics.GenericError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class FabrickException extends RuntimeException {

    private List<GenericError> errors;
    private HttpStatus httpStatus;

    public FabrickException(List<GenericError> errors, HttpStatus httpStatus) {
        super();
        this.errors = errors;
        this.httpStatus = httpStatus;
    }

    public FabrickException() {
        super();
    }

    public List<GenericError> getErrors() {
        return errors;
    }

    public void setErrors(List<GenericError> errors) {
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
