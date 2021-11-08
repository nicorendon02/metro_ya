package com.umanizales.metro_ya.model;

import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// using the Lombok Annotations
@Data
@NoArgsConstructor

// Generating the class with Attributes
public class BinaryTree {
    private Node root;
    private int counter;

    // method that calls the one in the Node Model later on...
    public void addUser(User data) throws BinaryTreeException
    {
        // validate whether the root is empty
        if(root==null)
        {
            // set a new root
            root = new Node(data);
        }
        // if the root already exists...
        else
        {
            // Call the method in the Node class to locate the Boy in the correct position!
            root.addUser(data);
        }
        // the BoysCount increases when finished.
        counter ++;
    }

    // method to ListUsers using Sorting Methods
    public List<User> listUsers(int which) throws DataNotFoundException
    {
        if (root == null)
        {
            throw new DataNotFoundException("There are no Users to show");
        }
        else {
            // Call to a certain method according to an Option...
            switch (which) {
                case 1:
                    return root.listUsersPreOrder();
                case 2:
                    return root.listUsersInOrder();
                case 3:
                    return root.listUsersPostOrder();
            }
            // if the List is empty
            throw new DataNotFoundException("No data to show");
        }
    }

    public void addReferred(int fatherId, User referredData) throws BinaryTreeException,
            DataNotFoundException
    {
        if(root == null)
        {
            throw new DataNotFoundException("There are no Users to show");
        }
        else
        {
            root.addReferred(fatherId,referredData);
        }
    }


    public void modifyReferred(int option, Node newData) throws DataNotFoundException
    {
        if(root == null)
        {
            throw new DataNotFoundException("There are no Users to show");
        }
        else
        {
            // Call to a certain method according to an Option...
            switch (option) {
                case 1:
                    root.modifyReferred(23,newData);
                    break;
                case 2:
                    root.modifyReferred(72,newData);
                    break;
            }
        }
    }

    // Method to delete a Boy by ID
    public void deleteReferred(int idToDelete) throws DataNotFoundException
    {
        // if the root has something
        if(root != null)
        {
            root.deleteReferred(idToDelete);

        }
        else
        {
            throw new DataNotFoundException("There are no Users yet");
        }
    }

    public int calculateDiscount(int userId) throws DataNotFoundException
    {
        if(root == null)
        {
            throw new DataNotFoundException("There are no Users yet");
        }
        else
        {
            return root.calculateDiscount(userId);
        }
    }
}
