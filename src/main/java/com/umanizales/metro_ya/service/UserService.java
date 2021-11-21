package com.umanizales.metro_ya.service;

import com.umanizales.metro_ya.application.dto.ResponseBinaryTreeDto;
import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.model.BinaryTree;
import com.umanizales.metro_ya.model.Node;
import com.umanizales.metro_ya.model.Train;
import com.umanizales.metro_ya.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


// Using Lombok Annotations
@Service

// Generating the class with Attributes
public class UserService {
    // Activate the process by creating the Tree!!
    private BinaryTree users = new BinaryTree();

    // ResponseEntity to fill the Tree with a List of Boys
    public ResponseEntity<ResponseBinaryTreeDto> fillTreeUsers(List<User> boys) throws BinaryTreeException
    {
        // for each Boy in the List...
        for(User boy:boys)
        {
            // add Boy to the Tree
            users.addUser(boy);
        }
        return new ResponseEntity<>(new ResponseBinaryTreeDto(true,"successful",
                null),HttpStatus.OK);
    }

    // ResponseEntity for addUser method
    public ResponseEntity<ResponseBinaryTreeDto> addUser(User user) throws BinaryTreeException
    {
        // call the first method located in BinaryTree
        users.addUser(user);
        // Once the Boy is added correctly...
        return new ResponseEntity<>(new ResponseBinaryTreeDto(user,"User added correctly!",
                null),HttpStatus.OK);
    }

    // ResponseEntity for listUsers methods
    public ResponseEntity<ResponseBinaryTreeDto> listUsers(int which) throws DataNotFoundException
    {
        return new ResponseEntity<>(new ResponseBinaryTreeDto(users.listUsers(which),"Successful!",
                null),HttpStatus.OK);

    }

    // ResponseEntity for addReferred method
    public ResponseEntity<ResponseBinaryTreeDto> addReferred(int fatherId, User referredData)
            throws BinaryTreeException,DataNotFoundException
    {
        // call the method in binaryTree
        users.addReferred(fatherId,referredData);

        return new ResponseEntity<>(new ResponseBinaryTreeDto(true,"Successful!",
                null),HttpStatus.OK);

    }

    // ResponseEntity for modifyReferred method
    public ResponseEntity<ResponseBinaryTreeDto> modifyReferred(int referredId, User newData)
            throws DataNotFoundException, BinaryTreeException
    {
        users.modifyReferred(referredId,newData);
        return new ResponseEntity<>(new ResponseBinaryTreeDto(true,"Successful!",
                null),HttpStatus.OK);

    }

    // ResponseEntity to find the father of a certain Boy' ID
    public ResponseEntity<ResponseBinaryTreeDto> deleteReferred(int idToDelete)
            throws DataNotFoundException, BinaryTreeException
    {
        users.deleteBoy(idToDelete);
        return new ResponseEntity<>(new ResponseBinaryTreeDto(true, "successful",
                null),HttpStatus.OK);
    }

    // ResponseEntity to find the father of a certain Boy' ID
    public ResponseEntity<ResponseBinaryTreeDto> calculateDiscount(int userId) throws DataNotFoundException
    {
        return new ResponseEntity<>(new ResponseBinaryTreeDto(true,
                "This User has a discount of "+users.calculateDiscount(userId)+"%",
                null),HttpStatus.OK);
    }

    public User checkUserExistences(int userId) throws DataNotFoundException
    {
        return users.checkUserExistences(userId);
    }

    public int calculateDiscounts(int userId) throws DataNotFoundException
    {
        return users.calculateDiscount(userId);
    }
}
