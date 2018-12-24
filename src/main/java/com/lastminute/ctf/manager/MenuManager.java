/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.manager;

import com.lastminute.ctf.entity.Flower;
import com.lastminute.ctf.store.Menu;
import com.lastminute.ctf.store.MenuItem;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mboniardi
 */
public class MenuManager {

    static Logger logger;
    static EntityManagerFactory emf_flowerWrite;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.manager.MenuManager.class);
        try {
            System.out.println("Init Starting Flowers Persistance!!!");
            emf_flowerWrite = Persistence.createEntityManagerFactory("flowers");
            System.out.println("Init complete Flowers Persistance!!!");
        } catch (Exception e) {
            logger.error("Error: Flowers Persistance initialization Problem!!!");
            logger.error(e);
        }
    }

    public static Menu getMenu() {
        Menu menu = new Menu();
        List<Flower> flowerList = null;
        try {
            EntityManager em_vgwr = emf_flowerWrite.createEntityManager();
            Query query = em_vgwr.createQuery("Select e from Flower e");
            flowerList = query.getResultList();
            for (Iterator<Flower> iterator = flowerList.iterator(); iterator.hasNext();) {
                Flower next = iterator.next();
                MenuItem menuItem = new MenuItem();
                menuItem.setText(next.getTitle());
                menuItem.setUrl("flowerDescription.action?flowerType="+next.getId());
                menu.addMenu(menuItem);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return menu;
    }
}
