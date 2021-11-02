package com.umanizales.metro_ya.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// using the Lombok Annotations
@Data
@NoArgsConstructor

public class NNode {
    private Route data;
    private List<Node> children;

    public void addRoute(){}

    public void listRoutes(){}

    public void deleteRoute(){}
}
