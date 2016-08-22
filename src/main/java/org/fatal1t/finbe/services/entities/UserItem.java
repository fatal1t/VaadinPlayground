/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services.entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author fatal1t
 */
public class UserItem {
    private long id;
    private String category;
    private double itemPrice;
    private String currency;
    private String itemName;
    private String itemLink;
    private String itemDesc;
    private Date purDate;
    private boolean isNeeded;

    public UserItem() {
        this.category = "default";
        this.currency = "CZK";
        this.itemName = "";
        this.itemLink = "";
        this.itemDesc = "";
    }

    public UserItem(long id, String category, double price, String currency, String itemName, String itemLink, String itemDesc, boolean isNeeded, Timestamp purDate) {
        this.id = id;
        this.category = category;
        this.itemPrice = price;
        this.currency = currency;
        this.itemName = itemName;
        this.itemLink = itemLink;
        this.itemDesc = itemDesc;
        this.purDate = Date.from(purDate.toInstant());
        this.isNeeded = isNeeded;  
    }
    public UserItem(long id, String category, double price, String currency, String itemName, String itemLink, String itemDesc, boolean isNeeded, Date purDate) {
        this.id = id;
        this.category = category;
        this.itemPrice = price;
        this.currency = currency;
        this.itemName = itemName;
        this.itemLink = itemLink;
        this.itemDesc = itemDesc;
        this.purDate = purDate;
        this.isNeeded = isNeeded;  
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Date getPurDate() {
        return purDate;
    }

    public void setPurDate(Date purDate) {
        this.purDate = purDate;
    }

    public boolean isIsNeeded() {
        return isNeeded;
    }

    public void setIsNeeded(boolean isNeeded) {
        this.isNeeded = isNeeded;
    }
    
    
}
