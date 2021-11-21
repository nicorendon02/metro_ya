package com.umanizales.metro_ya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// using the Lombok Annotations
@Data
@AllArgsConstructor

public class Ticket {
    @Positive
    @NotNull
    private double num;
    @Positive
    @NotNull
    private double seat;
    @Positive
    @NotEmpty
    private double price;
    private String holder;
}
