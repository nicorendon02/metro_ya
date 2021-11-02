package com.umanizales.metro_ya.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// using the Lombok Annotations
@Data
@NoArgsConstructor

public class Ticket {
    @Positive
    @NotNull
    private int num;
    @Positive
    @NotNull
    private int seat;
    @Positive
    @NotEmpty
    private float price;
    private User holder;
}
