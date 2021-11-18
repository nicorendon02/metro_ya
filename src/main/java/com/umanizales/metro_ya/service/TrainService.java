package com.umanizales.metro_ya.service;

import com.umanizales.metro_ya.application.dto.ResponseBinaryTreeDto;
import com.umanizales.metro_ya.controller.dto.RouteWithParentDTO;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.exception.NTreeException;
import com.umanizales.metro_ya.model.Train;
import com.umanizales.metro_ya.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Using Lombok Annotations
@Service

public class TrainService {
    // Activate the process by creating the Tree!!
    private Train train;

    public List<Train> trains = new ArrayList<>();

    // ResponseEntity to fill the MainList with a List of Trains
    public ResponseEntity<?> fillTrains(List<Train> trainsReceipt)
    {
        // for each Train in the List...
        // add Train to the List
        trains.addAll(trainsReceipt);
        return new ResponseEntity<>(new ResponseBinaryTreeDto(true,"successful",
                null),HttpStatus.OK);
    }


    public ResponseEntity<?> listTrains() throws DataNotFoundException
    {
        if(trains.isEmpty())
        {
            throw new DataNotFoundException("There are no Trains yet");
        }
        return new ResponseEntity<>(new ResponseBinaryTreeDto(trains,"successful",
                null),HttpStatus.OK);
    }
}
