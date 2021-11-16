package com.umanizales.metro_ya.controller;

import com.umanizales.metro_ya.controller.dto.ModifyReferredDTO;
import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.model.Node;
import com.umanizales.metro_ya.model.ReferredDTO;
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

    // fill a Tree using a List of Boys
    @PostMapping("/fill")
    public @ResponseBody
    ResponseEntity<?> fillTreeBoys(@RequestBody List<User> boys) throws BinaryTreeException {
        return userService.fillTreeUsers(boys);
    }

    @PostMapping("/addReferred")
    public @ResponseBody
    ResponseEntity<?> addReferred(@Valid @RequestBody ReferredDTO referred)
            throws BinaryTreeException, DataNotFoundException
    {
        return userService.addReferred(referred.getFatherId(),referred.getData());
    }

    @PostMapping("/modifyReferred")
    public @ResponseBody
    ResponseEntity<?> modifyReferred(@Valid @RequestBody ModifyReferredDTO boy)
            throws DataNotFoundException, BinaryTreeException
    {
        return userService.modifyReferred(boy.getReferredId(),boy.getNewData());
    }

    // find the father of a certain Boy' ID
    @GetMapping("/deleteById/{idToDelete}")
    public @ResponseBody
    ResponseEntity<?> deleteReferred(@PathVariable int idToDelete) throws DataNotFoundException {
        return userService.deleteReferred(idToDelete);
    }

    // calculate ticket discount
    @GetMapping("/discount/{userId}")
    public @ResponseBody
    ResponseEntity<?> calculateDiscount(@PathVariable int userId) throws DataNotFoundException {
        return userService.calculateDiscount(userId);
    }
}
