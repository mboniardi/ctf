/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.action;

import com.lastminute.ctf.manager.FlowerDescriptionManager;
import com.lastminute.ctf.store.FlowerDescription;
import com.lastminute.ctf.store.Menu;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mboniardi
 */
public class FlowerDescriptionAction extends ActionSupport {

    static Logger logger;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.action.FlowerDescriptionAction.class);
    }

    private FlowerDescription flowerDescription;
    private Menu menu;
    private String flowerType = "";

    public String execute() {
        logger.debug("FlowerType: " + flowerType);
        setMenu(FlowerDescriptionManager.getMenu());
        if (flowerType != null && flowerType.length() > 0) {
            flowerDescription = FlowerDescriptionManager.getFlowerDescription(flowerType);
        } else {
            flowerDescription = new FlowerDescription();
        }
        return "success";
    }

    public FlowerDescription getFlowerDescription() {
        return flowerDescription;
    }

    /**
     * @return the flowerType
     */
    public String getFlowerType() {
        return flowerType;
    }

    /**
     * @param flowerType the flowerType to set
     */
    public void setFlowerType(String flowerType) {
        this.flowerType = flowerType;
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
