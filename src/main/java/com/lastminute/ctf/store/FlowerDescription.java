/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.store;

import com.lastminute.ctf.entity.Flower;

/**
 *
 * @author mboniardi
 */
public class FlowerDescription {
    private String description;
    private Flower flower;
    
    public FlowerDescription () {
        description="This flower is not in the Catalog";
    }
    
    public FlowerDescription (String newDescription){
        description=newDescription;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * @return the flower
     */
    public Flower getFlower() {
        return flower;
    }

    /**
     * @param flower the flower to set
     */
    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
