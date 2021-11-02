package com.umanizales.metro_ya.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// using the Lombok Annotations
@Data
@AllArgsConstructor

public class UserType {
    private int code;
    private String description;
}
