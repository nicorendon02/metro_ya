package com.umanizales.metro_ya.service;

import com.umanizales.metro_ya.application.dto.ResponseBinaryTreeDto;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.exception.NTreeException;
import com.umanizales.metro_ya.model.NTree;
import com.umanizales.metro_ya.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Using Lombok Annotations
@Service
public class RouteService {
    private NTree nTree= new NTree();

    public ResponseEntity<ResponseBinaryTreeDto> addBoy(User boy, int parentIdentification)
            throws DataNotFoundException, NTreeException
    {
        nTree.add(boy,parentIdentification);
        return new ResponseEntity<>(
                new ResponseBinaryTreeDto(boy,"Boy added correctly!",
                        null), HttpStatus.OK);
    }

    /*
    public ResponseEntity<ResponseBinaryTreeDto> listBoys() throws DataNotFoundException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(nTree.getRoot(),"Successful!", null)
                ,HttpStatus.OK
        );
    }

     */

    public ResponseEntity<ResponseBinaryTreeDto> listBoys() throws NTreeException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(nTree.listBoys(),"Successful!", null)
                ,HttpStatus.OK
        );
    }
}
