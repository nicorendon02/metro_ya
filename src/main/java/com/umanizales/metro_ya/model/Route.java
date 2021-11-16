package com.umanizales.metro_ya.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// using the Lombok Annotations
@Data
@NoArgsConstructor

public class Route {
    @NotNull
    @Positive
    private int code;
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
    @NotNull
    @Positive
    private int price;
}
