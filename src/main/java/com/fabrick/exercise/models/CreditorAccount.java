package com.fabrick.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditorAccount {
    private String accountCode;
    //private String bicCode;
}
