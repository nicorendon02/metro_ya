package com.umanizales.metro_ya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

public class User {
    @NotNull
    @Positive
    public int identification;
    @NotNull
    @NotBlank
    @Size(max=50)
    public String name;
    @Positive
    @NotNull
    public byte age;
    @NotNull
    @NotBlank
    //@RegularExpression
    public String email;
    //@RegularExpression
    public String password;

    public UserType userType;

}