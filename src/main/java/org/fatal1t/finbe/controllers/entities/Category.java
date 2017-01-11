/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
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

/*id_cat bigint NOT NULL DEFAULT nextval('catids'::regclass),
id_user bigint,
cat_name character varying(100),
id_cat_type bigint,
create_date timestamp without time zone,*/
@Entity
@Table(name = "user_cats", schema = "public")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catids")
    @SequenceGenerator(name = "catids", sequenceName = "public.catids")
    @Column(name = "id_cat")
    private Long id;
    
    @Column(name = "id_user")
    private Long idUser;
    
    @Column(name = "cat_name")
    private String catName;
    
    @Column(name = "create_date")
    private Timestamp createDate;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    public Category()
    {
        this.catName = "";
        this.createDate = Timestamp.from(Instant.now());
    }
    
}

