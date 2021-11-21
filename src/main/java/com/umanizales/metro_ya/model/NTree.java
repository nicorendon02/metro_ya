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

    public void add(Route child, int parentIdentification) throws NTreeException, DataNotFoundException
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
            NNode childFind= root.findNTreeByIdentification(child.getCode());
            if(childFind!=null)
            {
                throw  new NTreeException("Ya existe un nodo con la identificación "+child.getCode());
            }
            if(parent.getChildren()==null)
                parent.setChildren(new ArrayList<>());
            parent.getChildren().add(new NNode(child));
        }
    }

    public void validateNtreeEmpty() throws NTreeException
    {
        if(this.root== null)
            throw  new NTreeException("NTree is empty");
    }


    public List<Route> listRoutes() throws NTreeException
    {
        validateNtreeEmpty();
        return root.listRoutes(new ArrayList<Route>());
    }

    public NNode printTreeWithChildren() throws DataNotFoundException
    {
        if(root == null)
        {
            throw new DataNotFoundException("There are no Routes yet");
        }
        else {
            return root.printTreeWithChildren();
        }
    }

    public NNode findNTreeByIdentification(int identification)
    {
        return root.findNTreeByIdentification(identification);
    }
}
