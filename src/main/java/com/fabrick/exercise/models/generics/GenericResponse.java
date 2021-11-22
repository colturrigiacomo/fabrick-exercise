package com.fabrick.exercise.models.generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private List<GenericError> errors;
    private T payload;
}
