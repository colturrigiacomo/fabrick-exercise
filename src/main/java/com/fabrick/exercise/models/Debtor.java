package com.fabrick.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Debtor {

    private String name;
    private CreditorAccount account;

}
