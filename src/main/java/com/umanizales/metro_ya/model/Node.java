package com.umanizales.metro_ya.model;

import com.umanizales.metro_ya.exception.BinaryTreeException;
import com.umanizales.metro_ya.exception.DataNotFoundException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@ToString
// Generating the class with Attributes
public class Node {
    private User data;
    private Node left;
    private Node right;

// generating constructor method
    public Node(User data) {
        this.data = data;
    }

    // addUser method signature (throws Exception in case of a mistake)
    public void addUser(User data) throws BinaryTreeException
    {
        // if the Node ID is < root ID
        if(data.getIdentification() < this.getData().getIdentification())
        {
            // if root Left == null
            if(this.getLeft()==null)
            {
                // root Left receives the Node
                this.setLeft(new Node(data));
            }
            // if root Left has something...
            else
            {
                // move to the Left and call the method AGAIN!
                this.left.addUser(data);
            }
        }
        // if the Node is > root ID
        else if (data.getIdentification() > this.getData().getIdentification())
        {
            // if root Right == null
            if(this.getRight()==null)
            {
                // the Node goes to the Right side
                this.setRight(new Node(data));
            }
            // if the root Right has something...
            else
            {
                // move to the Right and call the method AGAIN!
                this.right.addUser(data);
            }
        }
        // if the Node ID is EQUAL TO root ID
        else
        {
            // A node with that ID already exists. (Exception)
            throw new BinaryTreeException("A User with that ID already exists!");
        }
    }

    // ----- Sort Methods -----

    // Method preOrder
    public List<User> listUsersPreOrder() {
        // Generate a new List to append Boys
        List<User> listUsersPreOrder = new ArrayList<>();
        // List appends the root
        listUsersPreOrder.add(this.getData());
        // if root Left has something...
        if(this.getLeft() != null){
            // move to the Left and call the method AGAIN! and add ALL
            listUsersPreOrder.addAll(this.getLeft().listUsersPreOrder());
        }
        // if root Right has something...
        if(this.getRight() != null){
            // move to the Right and call the method AGAIN! and add ALL
            listUsersPreOrder.addAll(this.getRight().listUsersPreOrder());
        }
        // Once finished return the List
        return  listUsersPreOrder;
    }

    public List<User> listUsersInOrder(){
        // Generate a new List
        List<User> listUsersInOrder = new ArrayList<>();
        // if root Left has something...
        if(this.getLeft() != null){
            // move to the Left and call the method AGAIN! and add ALL
            listUsersInOrder.addAll(this.getLeft().listUsersInOrder());
        }
        // add root to the List
        listUsersInOrder.add(this.getData());

        // if the root Right has something...
        if(this.getRight() != null){
            // move to the Right and call the method AGAIN! and add ALL
            listUsersInOrder.addAll(this.getRight().listUsersInOrder());
        }
        // Once finished return the List
        return listUsersInOrder;
    }

    public List<User> listUsersPostOrder(){
        // Generate a new List
        List<User> listUsersPostOrder = new ArrayList<>();
        // if root Left has something...
        if(this.getLeft() != null){
            // move to the Left and call the method AGAIN! and add ALL
            listUsersPostOrder.addAll(this.getLeft().listUsersPostOrder());
        }
        // if the root Right has something...
        if(this.getRight() != null){
            // move to the Right and call the method AGAIN! and add ALL
            listUsersPostOrder.addAll(this.getRight().listUsersPostOrder());
        }
        // add the root to the List
        listUsersPostOrder.add(this.getData());
        // Once finished return the List
        return listUsersPostOrder;
    }

    // Method to add a new Referred to the Binary Tree
    public void addReferred(int fatherId, User referredData) throws BinaryTreeException, DataNotFoundException
    {
        if(this.getData().getIdentification() == fatherId)
        {
            if(referredData.getIdentification() < fatherId)
            {
                if(this.getLeft() != null)
                {
                    throw new BinaryTreeException("This User already has a referred assigned!");
                }
                this.setLeft(new Node(referredData));

            }
            if(this.getRight() != null)
            {
                throw new BinaryTreeException("This User already has a referred assigned!");
            }
            this.setRight(new Node(referredData));
        }
        else
        {
            if(fatherId < this.getData().getIdentification())
            {
                if(this.getLeft() != null)
                {
                    this.getLeft().addReferred(fatherId,referredData);
                }
                else
                {
                    throw new DataNotFoundException("The father was not found!");
                }
            }
            else
            {
                if(this.getRight() != null)
                {
                    this.getRight().addReferred(fatherId,referredData);
                }
                else
                {
                    throw new DataNotFoundException("The father was not found!");
                }
            }
        }
    }

    // Method to change a Referred data
    public void modifyReferred(int referredId, User newData) throws DataNotFoundException,
            BinaryTreeException
    {
        if(this.getData().getIdentification() == referredId)
        {
            if(!this.checkUserExistence(newData.getIdentification())) {
                this.setData(newData);
            }
            else
            {
                throw new BinaryTreeException("A User with that ID already exists!");
            }
        }
        else if(referredId < this.getData().getIdentification())
        {
            if(this.getLeft() != null)
            {
                if(this.getLeft().getData().getIdentification() == referredId)
                {
                    if(!this.checkUserExistence(newData.getIdentification())) {
                        this.getLeft().setData(newData);
                    }
                    else
                    {
                        throw new BinaryTreeException("A User with that ID already exists!");
                    }
                }
                else
                {
                    this.getLeft().modifyReferred(referredId,newData);
                }
            }
            else
            {
                throw new DataNotFoundException("This Referred does not exist!");
            }
        }
        else
        {
            if(this.getRight() != null)
            {
                if(this.getRight().getData().getIdentification() == referredId)
                {
                    if(!this.checkUserExistence(newData.getIdentification())) {
                        this.getRight().setData(newData);
                    }
                    else
                    {
                        throw new BinaryTreeException("A User with that ID already exists!");
                    }
                }
                else
                {
                    this.getRight().modifyReferred(referredId,newData);
                }
            }
            else
            {
                throw new DataNotFoundException("This Referred does not exist!");
            }
        }
    }

    public boolean checkUserExistence(int userId)
    {
        if(this.getData().getIdentification() == userId)
        {
            return true;
        }
        else if(userId < this.getData().getIdentification())
        {
            if(this.getLeft() != null)
            {
                return this.getLeft().checkUserExistence(userId);
            }
        }
        else
        {
            if(this.getRight() != null)
            {
                return this.getRight().checkUserExistence(userId);
            }
        }
        return false;
    }

    // validate if the Node is a Leaf
    public boolean isLeaf()
    {
        // return TRUE if the Node has Left & Right == null
        return left ==null && right==null;
    }

    // Method to find the Largest ID of the Tree
    public User findLargestId()
    {
        // if current on the Right has something...
        if(this.getRight() == null)
        {
            return this.getData();
        }
        // Move to the Right until the Method finds a Node without kids on the Right
        return this.getRight().findLargestId();
    }


    // Method to delete a Boy by ID
    public void deleteBoy(int idToDelete) throws DataNotFoundException
    {
        // if idToDelete < current ID
        if(idToDelete < this.data.getIdentification())
        {
            // if current on the Left has something...
            if(this.getLeft() != null)
            {
                // if my Left child ID is the Boy to delete
                if(this.getLeft().getData().getIdentification() == idToDelete)
                {
                    // if my Left child is a Leaf...
                    if(this.getLeft().isLeaf())
                    {
                        // delete my Left child
                        this.setLeft(null);
                    }
                    // if my Left child on his Left is null and on his Right has something...
                    else if(this.getLeft().getLeft() == null && this.getLeft().getRight() != null)
                    {
                        // my Left will be my Left child's Right
                        this.setLeft(this.getLeft().getRight());
                    }
                    // if my Left child on his Left has something and on his Right is null...
                    else if(this.getLeft().getLeft() != null && this.getLeft().getRight() == null)
                    {
                        // my Left will be my Left child's Left
                        this.setLeft(this.getLeft().getLeft());
                    }
                    else
                    {
                        // Here the Boy to delete has his Left & Right != null
                        // my Left child's Left finds his LargestBoy to capture the data
                        User dataTransfer = this.getLeft().getLeft().findLargestId();
                        // my Left child deletes that largestBoy
                        this.getLeft().deleteBoy(dataTransfer.getIdentification());
                        // replace my Left child's data with dataTransfer
                        this.getLeft().setData(dataTransfer);
                    }
                }
                // if my Left child is not the Boy to delete...
                else
                {
                    // move to the Left and ask again
                    this.getLeft().deleteBoy(idToDelete);
                }
            }
            // if the Method couldn't find the Boy to delete...
            else
            {
                throw new DataNotFoundException("This User does not exist");
            }
        }
        // if idToDelete > current ID
        // find on my Right...
        else
        {
            // if my Right has something...
            if(this.getRight() != null)
            {
                // if my Right child is the Boy to delete...
                if(this.getRight().getData().getIdentification() == idToDelete)
                {
                    // if my Right child is a Leaf...
                    if(this.getRight().isLeaf())
                    {
                        // delete my Right
                        this.setRight(null);
                    }
                    // if my Right child has his Left null and his Right has something...
                    else if(this.getRight().getLeft() == null && this.getRight().getRight() != null)
                    {
                        // my Right will be my Right child's Right
                        this.setRight(this.getRight().getRight());
                    }
                    // if my Right child has his Left has something and his Right is null...
                    else if(this.getRight().getLeft() != null && this.getRight().getRight() == null)
                    {
                        // my Right will be my Right child's Left
                        this.setRight(this.getRight().getLeft());
                    }
                    // if the Boy to delete has his Left & Right != null
                    else
                    {
                        // my Right child's Left finds his largestBoy to capture
                        User dataTransfer = this.getRight().getLeft().findLargestId();
                        // my Right child deletes that largestBoy
                        this.getRight().deleteBoy(dataTransfer.getIdentification());
                        // replace my Right child's data with dataTransfer
                        this.getRight().setData(dataTransfer);
                    }
                }
                // if my Right child is not the Boy to delete
                else
                {
                    // move to the Right and ask again
                    this.getRight().deleteBoy(idToDelete);
                }
            }
            // if the Method couldn't find the Boy to delete...
            else
            {
                throw new DataNotFoundException("This User does not exist");
            }
        }
    }


    public int calculateDiscount(int userId) throws DataNotFoundException
    {
        int discount = 0;
        if(this.getData().getIdentification() == userId) {
            if (this.getLeft() != null) {
                discount = discount + 6;
                if (this.getLeft().getLeft() != null) {
                    discount = discount + 2;
                }
                if (this.getLeft().getRight() != null) {
                    discount = discount + 2;
                }
            }
            if (this.getRight() != null) {
                discount = discount + 6;
                if (this.getRight().getLeft() != null) {
                    discount = discount + 2;
                }
                if (this.getRight().getRight() != null) {
                    discount = discount + 2;
                }
            }

        }

        else
        {
                if (userId < this.getData().getIdentification()) {
                    if (this.getLeft() != null) {
                        discount = discount + this.getLeft().calculateDiscount(userId);
                    } else {
                        throw new DataNotFoundException("This User ID does not exist!");
                    }
                } else {
                    if (this.getRight() != null) {
                        discount = discount + this.getRight().calculateDiscount(userId);
                    } else {
                        throw new DataNotFoundException("This User ID does not exist!");
                    }
                }
            }
        return discount;
    }
}
