/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.manager;

import com.lastminute.ctf.entity.Flower;
import com.lastminute.ctf.store.FlowerDescription;
import com.lastminute.ctf.store.FlowerList;
import com.lastminute.ctf.store.Menu;
import com.lastminute.ctf.store.MenuItem;
import java.util.ArrayList;
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
public class FlowerDescriptionManager {

    static Logger logger;
    static EntityManagerFactory emf_ctf;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.manager.FlowerDescriptionManager.class);
        try {
            logger.debug("Init Starting Flowers Persistance!!!");
            emf_ctf = Persistence.createEntityManagerFactory("flowers");
            logger.debug("Init complete Flowers Persistance!!!");
        } catch (Exception e) {
            logger.error("Error: Flowers Persistance initialization Problem!!!");
            logger.error(e);
        }
    }

/**
 * 
 * @param flowerID id to be used to collect information (from DB and from S3)
 * @return a FLowerDescription Object with data from DB and data from S£
 */
    public static FlowerDescription getFlowerDescription(String flowerID) {
        // get text from S3
        String result = S3Manager.getS3Text(S3Manager.getDescriptionPath(flowerID));
        // get data from DB
        FlowerDescription fDesc = new FlowerDescription(result);
        fDesc.setFlower(getFlowerDB(flowerID));
        return fDesc;
    }

 /**
  * 
  * @param flowerID identifier of the Flower
  * @return Flower object taken from DB
  */
    private static Flower getFlowerDB(String flowerID) {
        logger.info("Loading Flower " + flowerID);
        Flower newFlower = null;
        EntityManager em_ctf = null;
        try {
            em_ctf = emf_ctf.createEntityManager();
            newFlower = em_ctf.find(Flower.class, flowerID);
            logger.info("Loaded flower:" + newFlower.getTitle());
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (em_ctf != null) {
                em_ctf.close();
            }
        }
        return newFlower;
    }

    /**
     * Creating a Menu Object with all the manuItems (flower names and links)
     * @return Menu objects to be used in the menu jsp tile component
     */
    public static Menu getMenu() {
        Menu menu = new Menu();
        List<Flower> flowerList = null;
        EntityManager em_ctf = null;
        try {
            em_ctf = emf_ctf.createEntityManager();
            Query query = em_ctf.createQuery("Select e from Flower e");
            flowerList = query.getResultList();
            for (Flower next : flowerList) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(next.getTitle());
                menuItem.setUrl("flowerDescription.action?flowerType=" + next.getId());
                menu.addMenu(menuItem);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (em_ctf != null) {
                em_ctf.close();
            }
        }
        return menu;
    }

    /**
     * Get an FlowerList containing all Flowers available in DB
     * @return 
     */
    public static FlowerList getFlowerList() {
        FlowerList flowerList = new FlowerList();
        List<Flower> fList = null;
        EntityManager em_ctf = null;
        try {
            em_ctf = emf_ctf.createEntityManager();
            Query query = em_ctf.createQuery("Select e from Flower e");
            fList = query.getResultList();
            for (Flower next : fList) {
                flowerList.addFlower(next);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (em_ctf != null) {
                em_ctf.close();
            }
        }
        return flowerList;
    } 
    
    public static String createSqlInjection(String flowerID){
        EntityManager em_ctf = null;
        List<String> sqlResults ;
        StringBuilder sResult = new StringBuilder();
        try {
            em_ctf = emf_ctf.createEntityManager();
            Query query = em_ctf.createNativeQuery("Select id from FLOWER where id ='"+flowerID+"'");
            sqlResults = query.getResultList();
            for (String next : sqlResults) {
                sResult.append("|"+next+"|");
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (em_ctf != null) {
                em_ctf.close();
            }
        }
        return sResult.toString();
    }
}
