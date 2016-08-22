/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

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
/*  id_currency bigint NOT NULL DEFAULT nextval('currids'::regclass),
iso_code character varying(3),
curr_name character varying(100),
curr_value_czk double precision,
curr_date timestamp without time zone,*/
@Entity
@Table(name = "currency", schema = "public")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currids")
    @SequenceGenerator(name = "currids", sequenceName = "public.currids")
    @Column(name = "id_currency")
    private Long id;
    
    @Column(name = "curr_name")
    private String currencyName;
    
    @Column(name = "iso_code")
    private String isoCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
    
    
}
