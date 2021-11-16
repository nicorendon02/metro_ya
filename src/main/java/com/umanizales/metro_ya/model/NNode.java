package com.umanizales.metro_ya.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Data
public class NNode {
    private User data;
    private List<NNode> children;

    public NNode(User data) {
        this.data = data;

    }


    public NNode findNTreeByIdentification(int identification)
    {
        if(this.getData().getIdentification()==identification)
        {
            return this;
        }
        else
        {
            if(this.children!=null) {
                for (NNode child : children) {
                    NNode nodeFind = child.findNTreeByIdentification(identification);
                    if (nodeFind != null) {
                        return nodeFind;
                    }
                }
            }

        }
        return null;

    }

    public List<User> listBoys(List<User> listBoys)
    {
        listBoys.add(this.getData());
        if(this.children != null) {
            for (NNode boy : this.children) {
                boy.listBoys(listBoys);
            }
        }
        return listBoys;
    }
}
