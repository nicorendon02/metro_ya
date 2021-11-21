package com.umanizales.metro_ya.service;

import com.umanizales.metro_ya.application.dto.ResponseBinaryTreeDto;
import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class TripService {
    private Trip trip;
    private BinaryTree usersTree;
    private NNode nNode;
    private Node node;
    @Autowired
    private TrainService trainService;
    @Autowired
    private UserService userService;
    @Autowired
    private RouteService routeService;
    int tripCounter = 1;
    int trainCounter = 7;

    List<Trip> trips = new ArrayList<>();

    public float calculateTripPrice(int routeId, int userId) throws DataNotFoundException
    {
        //int i = 100;
        int routePrice = routeService.findRouteById(routeId).getData().getPrice();
        int userDiscount = userService.calculateDiscounts(userId);

        float tripPrice1 = routePrice/100f;
        float tripPrice2 = 100f-userDiscount;
        float total = tripPrice1*tripPrice2;
        return total;
    }

    public NNode bringRoute(int idFind) throws DataNotFoundException
    {
        return routeService.findRouteById(idFind);
    }

    public User bringUser(int idFind) throws DataNotFoundException
    {
        return userService.checkUserExistences(idFind);
    }


    public void createTrip(int routeId, int userId) throws DataNotFoundException
    {
        NNode routeTemp = new NNode(bringRoute(routeId).getData());
        List<Train> trainsTemp = trainService.trains;
        List<Ticket> tickets = new ArrayList<>();
        Trip trip = new Trip(tripCounter,routeTemp,trainsTemp.get(1),calculateTripPrice(routeId,userId),tickets);
        tripCounter++;
        trainCounter--;

        trips.add(trip);
    }

    public List<Trip> listTrips() throws DataNotFoundException
    {
        if(trips.isEmpty())
        {
            throw new DataNotFoundException("There are no Trips yet");
        }
        return trips;
    }



    public void cancelTrip(int idFind)
    {
        for(int i=0; i<trips.size(); i++)
        {
            if(trips.get(i).getIdentification() == idFind)
            {
                trips.remove(i);
                break;
            }
        }
    }



    public ResponseEntity<?> createTicket(int tripId, int userId) throws DataNotFoundException {
       // num = (int) (Math.random())
        for(int i=0; i<trips.size(); i++)
        {
            if(trips.get(i).getIdentification() == tripId)
            {
                double temp = trips.get(i).getPrice();
                User temp2 = userService.checkUserExistences(userId);
                Ticket ticket = new Ticket(Math.random(),Math.random(),temp, temp2.getName());
                trips.get(i).getTickets().add(ticket);
                break;
            }
        }
        return new ResponseEntity<ResponseBinaryTreeDto>
                (new ResponseBinaryTreeDto(true, "successful", null), HttpStatus.OK);
    }


    public ResponseEntity<ResponseBinaryTreeDto> createTrips(int routeId, int userId)
            throws DataNotFoundException
    {
        this.createTrip(routeId,userId);
        return new ResponseEntity<ResponseBinaryTreeDto>
                (new ResponseBinaryTreeDto(true, "successful", null), HttpStatus.OK);
    }



    public ResponseEntity<ResponseBinaryTreeDto> listTheTrips()
            throws DataNotFoundException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>
                (new ResponseBinaryTreeDto(listTrips(), "successful", null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseBinaryTreeDto> cancelTrips(int idTrip)
            throws DataNotFoundException, BinaryTreeException
    {
        cancelTrip(idTrip);
        return new ResponseEntity<ResponseBinaryTreeDto>
                (new ResponseBinaryTreeDto(true, "successful", null), HttpStatus.OK);
    }




    public ResponseEntity<ResponseBinaryTreeDto> bringRoutes(int idFind)
            throws DataNotFoundException, BinaryTreeException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>
                (new ResponseBinaryTreeDto(bringRoute(idFind), "successful", null), HttpStatus.OK);
    }
}
