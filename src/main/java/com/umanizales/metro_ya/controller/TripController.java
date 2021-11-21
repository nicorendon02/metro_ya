package com.umanizales.metro_ya.controller;

import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.model.User;
import com.umanizales.metro_ya.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "trip")
@Validated
@CrossOrigin("http://localhost:8080")
public class TripController {
    @Autowired
    private TripService tripService;


    @GetMapping("/create/{routeId}/{userId}")
    public @ResponseBody
    ResponseEntity<?> createTrip(@PathVariable int routeId,@PathVariable int userId)
            throws DataNotFoundException, BinaryTreeException {
        return tripService.createTrips(routeId,userId);
    }

    @GetMapping("/list")
    public @ResponseBody
    ResponseEntity<?> listTrips() throws DataNotFoundException {
        return tripService.listTheTrips();
    }

    @GetMapping("/cancel/{idTrip}")
    public @ResponseBody
    ResponseEntity<?> cancelTrip(@PathVariable int idTrip)
            throws DataNotFoundException, BinaryTreeException {
        return tripService.cancelTrips(idTrip);
    }


    @GetMapping("/ticket/{tripId}/{userId}")
    public @ResponseBody
    ResponseEntity<?> createTicket(@PathVariable int tripId, @PathVariable int userId)
            throws DataNotFoundException {
        return tripService.createTicket(tripId, userId);
    }



    @GetMapping("/bring/{idFind}")
    public @ResponseBody
    ResponseEntity<?> bringRoute(@PathVariable int idFind)
            throws DataNotFoundException, BinaryTreeException {
        return tripService.bringRoutes(idFind);
    }
}
