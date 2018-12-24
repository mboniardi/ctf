/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.store;

import com.lastminute.ctf.entity.Flower;
import java.util.ArrayList;

/**
 *
 * @author mboniardi
 */
public class FlowerList {
    private ArrayList<Flower> items = new ArrayList<>();

    /**
     * @return the items
     */
    public ArrayList<Flower> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<Flower> items) {
        this.items = items;
    }
    
    public void addFlower(Flower flower){
        this.items.add(flower);
    }
    
}
