package com.umanizales.metro_ya.service;

import com.umanizales.metro_ya.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

// Using Lombok Annotations
@Service

public class TrainService {
    // Activate the process by creating the Tree!!
    private Train train = new Train();

    private List<Train> trains;
}
