package com.umanizales.metro_ya.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ReferredDTO {
    @Valid
    @NotNull
    @Positive
    private int fatherId;
    @Valid
    @NotNull
    private User data;
}
