package com.umanizales.metro_ya.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
// using the Lombok Annotations
@Data
@NoArgsConstructor

public class Trip {
    private Route route;
    private Train train;
    private String state;
    private String date;
    private String time;
    private float price;
    private List<Ticket> tickets;

    public void buyTrip(){}

    //public List<Trip> listTrips(){}

    public void cancelTrip(){}

    //public float calculateTripPrice(){}
}
