package com.fabrick.exercise.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private Integer status;
    private String error;
    private String message;
    private String code;
    private String path;

    public void setStatus(HttpStatus status) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }
}