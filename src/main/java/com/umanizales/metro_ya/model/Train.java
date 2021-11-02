package com.umanizales.metro_ya.model;

import lombok.Data;
import lombok.NoArgsConstructor;

// using the Lombok Annotations
@Data
@NoArgsConstructor

public class Train {
    private int identification;
    private int capacity;
}
