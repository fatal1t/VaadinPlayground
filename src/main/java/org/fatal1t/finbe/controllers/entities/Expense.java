/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fatal1t
 *   id_exp bigint NOT NULL DEFAULT nextval('expsids'::regclass),
  id_user bigint,
  expense_name character varying(100),
  expense_place character varying(100),
  expense_price double precision,
  expense_curr bigint,
  expense_date timestamp without time zone,
 */
@Entity
@Table(name =  "user_expens", schema = "public")
public class Expense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expsids")
    @SequenceGenerator(name = "expsids", sequenceName = "expsids")
    @Column(name="id_exp")
    private Long id;
    @Column(name = "id_user")
    private Long userId;
    @Column(name = "expense_name")
    private String expName;
    @Column(name = "expense_price")
    private Double expPrice;
    @Column(name = "expense_cat")
    private Long expCat;
    
    @Column(name="expense_place")
    private String expPlace;
    @Column(name="expense_curr")
    private Long currId;
    @Column(name="expense_date")
    private Timestamp expDate;
    //private Long expItems;
    
    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name = "id_exp", referencedColumnName = "id_exp" )
    private List<ExpenseItem> expItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public Double getExpPrice() {
        return expPrice;
    }

    public void setExpPrice(Double expPrice) {
        this.expPrice = expPrice;
    }

    public String getExpPlace() {
        return expPlace;
    }

    public void setExpPlace(String expPlace) {
        this.expPlace = expPlace;
    }

    public Long getCurrId() {
        return currId;
    }

    public void setCurrId(Long currId) {
        this.currId = currId;
    }

    public Timestamp getExpDate() {
        return expDate;
    }

    public void setExpDate(Timestamp expDate) {
        this.expDate = expDate;
    }

    public List<ExpenseItem> getExpItems() {
        return expItems;
    }

    public void setExpItems(List<ExpenseItem> expItems) {
        this.expItems = expItems;
    }

    public Long getExpCat() {
        return expCat;
    }

    public void setExpCat(Long expCat) {
        this.expCat = expCat;
    }
    
}
