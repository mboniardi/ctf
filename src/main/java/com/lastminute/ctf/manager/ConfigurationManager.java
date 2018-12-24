/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.manager;

import com.lastminute.ctf.entity.Configurations;
import com.lastminute.ctf.store.AWSconfiguration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;

/**
 *
 * @author mboniardi
 */
public class ConfigurationManager {

    static Logger logger;
    static EntityManagerFactory emf_ctf;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.manager.ConfigurationManager.class);
        try {
            logger.debug("Init Starting Flowers Persistance!!!");
            emf_ctf = Persistence.createEntityManagerFactory("flowers");
            logger.debug("Init complete Flowers Persistance!!!");
        } catch (Exception e) {
            logger.error("Error: Flowers Persistance initialization Problem!!!");
            logger.error(e);
        }
    }

    public static AWSconfiguration getAWSConfiguration() throws Exception {
        logger.info("Loading AWS configuration");
        String configurationID = "AWS";
        AWSconfiguration S3conf = new AWSconfiguration();
        EntityManager em_ctf = null;
        try {
            em_ctf = emf_ctf.createEntityManager();
            Configurations conf = em_ctf.find(Configurations.class, configurationID);
            logger.debug("Loaded configuration:" + conf.getContent());
            JSONObject obj = new JSONObject(conf.getContent());
            S3conf.setRegion(obj.getJSONObject("aws_configuration").getString("region"));
            S3conf.setBucket(obj.getJSONObject("aws_configuration").getString("bucket"));
            S3conf.setAccessKey(obj.getJSONObject("aws_configuration").getString("accessKey"));
            S3conf.setSecretKey(obj.getJSONObject("aws_configuration").getString("secretKey"));
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (em_ctf != null) {
                em_ctf.close();
            }
        }
        return S3conf;
    }

}
