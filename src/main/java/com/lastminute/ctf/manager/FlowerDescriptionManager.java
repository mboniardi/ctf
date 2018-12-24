/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.manager;

import com.lastminute.ctf.entity.Flower;
import com.lastminute.ctf.store.FlowerDescription;
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
    static EntityManagerFactory emf_flowerWrite;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.manager.FlowerDescriptionManager.class);
        try {
            logger.debug("Init Starting Flowers Persistance!!!");
            emf_flowerWrite = Persistence.createEntityManagerFactory("flowers");
            logger.debug("Init complete Flowers Persistance!!!");
        } catch (Exception e) {
            logger.error("Error: Flowers Persistance initialization Problem!!!");
            logger.error(e);
        }
    }

    private static String getDescriptionPath(String flowerID) {
        return "/descriptions/" + flowerID + ".txt";
    }

    public static FlowerDescription getFlowerDescription(String flowerID) {
        // get text from S3
        String result = S3Manager.getS3Text(getDescriptionPath(flowerID));
        // get data from DB
        FlowerDescription fDesc = new FlowerDescription(result);
        fDesc.setFlower(getFlowerDB(flowerID));
        return fDesc;
    }

    private static Flower getFlowerDB(String flowerID) {
        logger.info("Loading Flower "+flowerID);
        Flower newFlower = null;
        EntityManager em_ctf=null;
        try {
            em_ctf = emf_flowerWrite.createEntityManager();
            newFlower = em_ctf.find(Flower.class, flowerID);
            logger.info("Loaded flower:" + newFlower.getTitle());
            System.out.println("Loaded flower:" + newFlower.getTitle());
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (em_ctf != null) {
                em_ctf.close();
            }
        }
        return newFlower;
    }
    

    


}
