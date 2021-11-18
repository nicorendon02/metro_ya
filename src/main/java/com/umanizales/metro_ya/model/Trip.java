package com.umanizales.metro_ya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

    private Node node;
    private NNode nNode;

    public void buyTrip(int userId, int routeId)
    {
        node.checkUserExistence(userId);
    }

    //public List<Trip> listTrips(){}

    public void cancelTrip(){}

    //public float calculateTripPrice(){}
}
