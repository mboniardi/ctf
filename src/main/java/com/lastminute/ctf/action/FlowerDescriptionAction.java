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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author mboniardi
 */
public class FlowerDescriptionAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

    static Logger logger;

    static {
        logger = LogManager.getLogger(com.lastminute.ctf.action.FlowerDescriptionAction.class);
    }

    private FlowerDescription flowerDescription;
    private Menu menu;
    private String flowerType = "";
    private static final String COOKIE_CONTENT = "VGhlRmlyc3RLZXlWYWx1ZQ==";
    private String sqlResult = "";

    public String execute() {
        // Save to cookie
        Cookie div = new Cookie("the1stKey", COOKIE_CONTENT);
        div.setMaxAge(60 * 60 * 24 * 365); // Make the cookie last a year
        servletResponse.addCookie(div);

        logger.debug("FlowerType: " + flowerType);
        // load menu
        setMenu(FlowerDescriptionManager.getMenu());

        if (flowerType != null && flowerType.length() > 0) {
            // load flowerDescription
            flowerDescription = FlowerDescriptionManager.getFlowerDescription(flowerType);
            // esecute the Query to allow SQL injection
            sqlResult = FlowerDescriptionManager.createSqlInjection(flowerType);
        } else {
            // default values
            flowerDescription = new FlowerDescription();
            sqlResult = "";
        }

        return "success";
    }

    // For access to the raw servlet request / response, eg for cookies
    protected HttpServletResponse servletResponse;

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    protected HttpServletRequest servletRequest;

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
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

    /**
     * @return the sqlResult
     */
    public String getSqlResult() {
        return sqlResult;
    }

    /**
     * @param sqlResult the sqlResult to set
     */
    public void setSqlResult(String sqlResult) {
        this.sqlResult = sqlResult;
    }
}
