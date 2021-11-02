package com.umanizales.metro_ya.controller;

import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.model.Node;
import com.umanizales.metro_ya.model.User;
import com.umanizales.metro_ya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// Using Lombok Annotations...
@RestController
@RequestMapping(path = "binarytree")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public @ResponseBody
    ResponseEntity<?> addUser(@Valid @RequestBody User user) throws BinaryTreeException {
        return userService.addUser(user);
    }

    @GetMapping("/preorder")
    public @ResponseBody
    ResponseEntity<?> listUsersPreOrder() throws DataNotFoundException {
        return userService.listUsers(1);
    }

    @GetMapping("/inorder")
    public @ResponseBody
    ResponseEntity<?> listUsersInOrder() throws DataNotFoundException {
        return userService.listUsers(2);
    }

    @GetMapping("/postorder")
    public @ResponseBody
    ResponseEntity<?> listUsersPostOrder() throws DataNotFoundException {
        return userService.listUsers(3);
    }

    @PostMapping("/newReferred1")
    public @ResponseBody
    ResponseEntity<?> addReferred1(@Valid @RequestBody User referredData)
            throws BinaryTreeException, DataNotFoundException
    {
        return userService.addReferred(1,referredData);
    }

    @PostMapping("/newReferred2")
    public @ResponseBody
    ResponseEntity<?> addReferred2(@Valid @RequestBody User referredData)
            throws BinaryTreeException, DataNotFoundException
    {
        return userService.addReferred(2,referredData);
    }

    @PostMapping("/modifyReferred1")
    public @ResponseBody
    ResponseEntity<?> modifyReferred1(@Valid @RequestBody User newData)
            throws BinaryTreeException, DataNotFoundException
    {
        return userService.modifyReferred(1,newData);
    }

    @PostMapping("/modifyReferred2")
    public @ResponseBody
    ResponseEntity<?> modifyReferred2(@Valid @RequestBody User newData)
            throws BinaryTreeException, DataNotFoundException
    {
        return userService.modifyReferred(2,newData);
    }

    // fill a Tree using a List of Boys
    @PostMapping("/fill")
    public @ResponseBody
    ResponseEntity<?> fillTreeBoys(@RequestBody List<User> boys) throws BinaryTreeException {
        return userService.fillTreeUsers(boys);
    }

    // find the father of a certain Boy' ID
    @GetMapping("/deleteById/{idToDelete}")
    public @ResponseBody
    ResponseEntity<?> deleteReferred(@PathVariable int idToDelete) throws DataNotFoundException {
        return userService.deleteReferred(idToDelete);
    }

    // find the father of a certain Boy' ID
    @GetMapping("/discount/{userId}")
    public @ResponseBody
    ResponseEntity<?> calculateDiscount(@PathVariable int userId) throws DataNotFoundException {
        return userService.calculateDiscount(userId);
    }
}
