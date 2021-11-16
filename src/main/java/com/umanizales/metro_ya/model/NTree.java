package com.umanizales.metro_ya.model;

import com.umanizales.metro_ya.exception.DataNotFoundException;
import com.umanizales.metro_ya.exception.NTreeException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// using the Lombok Annotations
@Data
public class NTree {
    private NNode root;
    private int count;

    public void add(User child, int parentIdentification) throws NTreeException, DataNotFoundException
    {
        if(root==null)
        {
            root = new NNode(child);
        }
        else
        {
            NNode parent= root.findNTreeByIdentification(parentIdentification);
            if(parent==null)
            {
                throw  new DataNotFoundException("No existe un nodo con la identificación "+parentIdentification);
            }
            NNode childFind= root.findNTreeByIdentification(child.getIdentification());
            if(childFind!=null)
            {
                throw  new NTreeException("Ya existe un nodo con la identificación "+child.getIdentification());
            }
            if(parent.getChildren()==null)
                parent.setChildren(new ArrayList<>());
            parent.getChildren().add(new NNode(child));
        }
    }
    /*
    public NNode findNTreeByIdentification(int identification) throws DataNotFoundException,NTreeException
    {
        validateNtreeEmpty();
    }
    *
     */

    public void validateNtreeEmpty() throws NTreeException
    {
        if(this.root== null)
            throw  new NTreeException("NTree is empty");
    }


    public List<User> listBoys() throws NTreeException
    {
        validateNtreeEmpty();
        return root.listBoys(new ArrayList<User>());
    }

}
