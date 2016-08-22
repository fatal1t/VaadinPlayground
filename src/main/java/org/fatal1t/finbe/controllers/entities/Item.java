/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fatal1t
 */
@Entity
@Table(name = "user_items", schema = "public")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemids")
    @SequenceGenerator(name = "itemids", sequenceName = "public.itemids")
    @Column(name = "id_item")
    private Long idItem;
    
    @Column(name = "id_cat")    
    private Long idCat;
    
    @Column(name = "id_user")
    private Long idUser;
    
    @Column(name = "item_name")
    private String itemName;
    
    @Column(name = "item_price")
    private Double itemPrice;
    
    @Column(name = "id_currency")
    private Long idCurrency;
    
    @Column(name = "item_desc")
    private String itemDesc;
    
    @Column(name = "is_needed")
    private Boolean isNeeded;
    
    @Column(name = "item_link")
    private String itemLink;
    
    @Column(name = "item_pur_date")
    private Timestamp itemPurDate;

    public Long getId() {
        return idItem;
    }

    public void setId(Long id) {
        this.idItem = id;
    }

    public Long getIdItem() {
        return idItem;
    }

    public Long getIdCat() {
        return idCat;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public Long getIdCurrency() {
        return idCurrency;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public Boolean getIsNeeded() {
        return isNeeded;
    }

    public String getItemLink() {
        return itemLink;
    }

    public Timestamp getItemPurDate() {
        return itemPurDate;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setIdCurrency(Long idCurrency) {
        this.idCurrency = idCurrency;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public void setIsNeeded(Boolean isNeeded) {
        this.isNeeded = isNeeded;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public void setItemPurDate(Timestamp itemPurDate) {
        this.itemPurDate = itemPurDate;
    }

    public Item() {
    }

    public Item(Long idItem, Long idCat, Long idUser, String itemName, Double itemPrice, Long idCurrency, String itemDesc, Boolean isNeeded, String itemLink, Timestamp itemPurDate) {
        this.idItem = idItem;
        this.idCat = idCat;
        this.idUser = idUser;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.idCurrency = idCurrency;
        this.itemDesc = itemDesc;
        this.isNeeded = isNeeded;
        this.itemLink = itemLink;
        this.itemPurDate = itemPurDate;
    }
    public Item(Long idCat, Long idUser, String itemName, Double itemPrice, Long idCurrency, String itemDesc, Boolean isNeeded, String itemLink, Timestamp itemPurDate) {
        this.idCat = idCat;
        this.idUser = idUser;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.idCurrency = idCurrency;
        this.itemDesc = itemDesc;
        this.isNeeded = isNeeded;
        this.itemLink = itemLink;
        this.itemPurDate = itemPurDate;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.fatal1t.finbe.controllers.entities.Item[ id=" + idItem + " ]";
    }
    
}
