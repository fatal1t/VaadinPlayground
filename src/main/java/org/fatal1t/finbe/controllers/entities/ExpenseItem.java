/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fatal1t
 * 	id_exp_item bigint NOT NULL DEFAULT nextval('expitemsids'),
	id_exp bigint,
	exp_item_mane varchar(200),
	exp_item_price double precision, 
	exp_item_desc varchar(500),
	exp_item_weight double precision,
	exp_item_weight_price double precision,
 * 
 * 
 * 
 * 
 */
@Entity
@Table(name = "expens_items", schema = "public")
class ExpenseItem implements Serializable {
   
    @Id
    @GeneratedValue(generator = "expitemsids", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "expitemsids", sequenceName = "expitemsids")
    @Column(name = "id_exp_item")
    private Long id;
    
    @Column(name = "exp_item_mane")
    private String itemName;
    
    @Column(name = "exp_item_price")
    private Double itemPrice;
    
    @Column(name = "exp_item_desc")
    private String itemDesc;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_exp")
    private Expense expense;
    
    @Column(name = "exp_item_weight")
    private Double itemWeight;
    
    @Column(name = "exp_item_weight_price")
    private Double pricePerWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Double getPricePerWeight() {
        return pricePerWeight;
    }

    public void setPricePerWeight(Double pricePerWeight) {
        this.pricePerWeight = pricePerWeight;
    }
    
    
}
