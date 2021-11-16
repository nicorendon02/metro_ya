package com.umanizales.metro_ya.service;

import com.umanizales.metro_ya.application.dto.ResponseBinaryTreeDto;
import com.umanizales.metro_ya.controller.dto.RouteWithParentDTO;
import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.exception.NTreeException;
import com.umanizales.metro_ya.model.NTree;
import com.umanizales.metro_ya.model.Route;
import com.umanizales.metro_ya.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Using Lombok Annotations
@Service
public class RouteService {
    private NTree nTree= new NTree();

    public ResponseEntity<ResponseBinaryTreeDto> addRoute(Route boy, int parentIdentification)
            throws DataNotFoundException, NTreeException
    {
        nTree.add(boy,parentIdentification);
        return new ResponseEntity<>(
                new ResponseBinaryTreeDto(boy,"Route added correctly!",
                        null), HttpStatus.OK);
    }

    // ResponseEntity to fill the Tree with a List of Routes
    public ResponseEntity<ResponseBinaryTreeDto> fillRoutes(List<RouteWithParentDTO> routes)
            throws NTreeException, DataNotFoundException
    {
        // for each Route in the List...
        for(RouteWithParentDTO route:routes)
        {
            // add Boy to the Tree
            nTree.add(route.getRoute(),route.getParentId());
        }
        return new ResponseEntity<>(new ResponseBinaryTreeDto(true,"successful",
                null),HttpStatus.OK);
    }

    public ResponseEntity<ResponseBinaryTreeDto> listRoutes() throws NTreeException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(nTree.listRoutes(),"Successful!", null)
                ,HttpStatus.OK
        );
    }
}
