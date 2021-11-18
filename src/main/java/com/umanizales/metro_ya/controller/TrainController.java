package com.umanizales.metro_ya.controller;

import com.umanizales.metro_ya.controller.dto.RouteWithParentDTO;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.exception.NTreeException;
import com.umanizales.metro_ya.model.Train;
import com.umanizales.metro_ya.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "train")
@Validated
@CrossOrigin("http://localhost:8080")

public class TrainController {
    @Autowired
    private TrainService trainService;

    @PostMapping("/fill")
    public @ResponseBody
    ResponseEntity<?> fillTrains(@RequestBody List<Train> trains)
    {
        return trainService.fillTrains(trains);
    }

    @GetMapping("/list")
    public
    ResponseEntity<?> listTrains() throws DataNotFoundException
    {
        return trainService.listTrains();
    }
}
