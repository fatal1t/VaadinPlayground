/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fatal1t
 */
public interface ItemRespository  extends CrudRepository<Item, Long>
{
    Item findByIdItem(Long idItem);
    Item findByIdItemAndIdUser(Long idItem, Long userId);
    List<Item> findByIdUser(Long idUser);
    List<Item> findByIdUserAndIdCat(Long idUser, Long idCat);    
}
