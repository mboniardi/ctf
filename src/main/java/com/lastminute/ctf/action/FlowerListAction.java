/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.action;

import com.lastminute.ctf.manager.FlowerDescriptionManager;
import com.lastminute.ctf.store.FlowerList;
import com.lastminute.ctf.store.Menu;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mboniardi
 */
public class FlowerListAction extends ActionSupport {

    static Logger logger;
    private Menu menu;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.action.FlowerDescriptionAction.class);
    }

    private FlowerList flowerList;

    /**
     * @return the flowerList
     */
    public FlowerList getFlowerList() {
        return flowerList;
    }

    /**
     * @param flowerList the flowerList to set
     */
    public void setFlowerList(FlowerList flowerList) {
        this.flowerList = flowerList;
    }

    public String execute() {
        setMenu(FlowerDescriptionManager.getMenu());
        setFlowerList(FlowerDescriptionManager.getFlowerList());
        return "success";
    }

    /**
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
