package com.umanizales.metro_ya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
// using the Lombok Annotations
@Data
@AllArgsConstructor

public class Trip {
    private int identification;
    private NNode route;
    private Train train;
    private float price;
    private List<Ticket> tickets;

}
