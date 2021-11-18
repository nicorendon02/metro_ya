package com.umanizales.metro_ya.controller;

import com.umanizales.metro_ya.controller.dto.RouteWithParentDTO;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.exception.NTreeException;
import com.umanizales.metro_ya.model.Route;
import com.umanizales.metro_ya.model.User;
import com.umanizales.metro_ya.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "ntree")
@Validated
@CrossOrigin("http://localhost:8080")
public class RouteController {
    @Autowired
    private RouteService nTreeService;

    @PostMapping("/add")
    public @ResponseBody
    ResponseEntity<?> addRoute(@Valid @RequestBody RouteWithParentDTO newRoute)
            throws DataNotFoundException, NTreeException {
        return nTreeService.addRoute(newRoute.getRoute(), newRoute.getParentId());
    }

    @PostMapping("/fill")
    public @ResponseBody
    ResponseEntity<?> fillRoutes(@RequestBody List<RouteWithParentDTO> routes)
            throws DataNotFoundException, NTreeException {
        return nTreeService.fillRoutes(routes);
    }

    @GetMapping("/list")
    public @ResponseBody
    ResponseEntity<?> listRoutes() throws NTreeException {
        return nTreeService.listRoutes();
    }

    @GetMapping("/print")
    public @ResponseBody
    ResponseEntity<?> printTreeWithChildren() throws DataNotFoundException {
        return nTreeService.printTreeWithChildren();
    }
}