/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute.ctf.manager;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.lastminute.ctf.store.AWSconfiguration;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mboniardi
 */
public class S3Manager {

    static org.apache.logging.log4j.Logger logger;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.manager.S3Manager.class);
    }

    public static String getS3Text(String key) {
        AWSconfiguration conf;
        String result = "";
        try {
            conf = ConfigurationManager.getAWSConfiguration();
            result = getS3ObjectContentAsString(conf, key);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return result;
    }

    public static String getS3ObjectContentAsString(AWSconfiguration conf, String key) {
        AWSCredentials credentials = new BasicAWSCredentials(conf.getAccessKey(), conf.getSecretKey());
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(conf.getRegion())
                .build();
        StringBuilder textBuilder = new StringBuilder();
        try {
            //remove / from name
            if (key.startsWith("/")) {
                key = key.substring(1);
            }
            if (key.endsWith("/")) {
                key = key.substring(0, key.length());
            }
            try (InputStream is = s3Client.getObject(conf.getBucket(), key).getObjectContent()) {
                try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
                    int c = 0;
                    while ((c = reader.read()) != -1) {
                        textBuilder.append((char) c);
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return textBuilder.toString();
    }
}
