/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.store;

import java.util.ArrayList;

/**
 *
 * @author mboniardi
 */
public class Menu {
    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    /**
     * @return the menuItems
     */
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * @param menuItems the menuItems to set
     */
    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    
    public void addMenu(MenuItem menuItem){
        this.menuItems.add(menuItem);
    }
    
    public int getSize(){
        return menuItems.size();
    }
}
