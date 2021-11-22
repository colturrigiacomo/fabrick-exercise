package com.fabrick.exercise.models.generics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String description;
    @JsonIgnore
    private String params;

    public GenericError(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
