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
public interface CategoryRepository extends CrudRepository<Category, Long>{
    public Category findById(Long id);
    public List<Category> findByIdUser(Long idUser);
    public Category findByIdUserAndCatName(Long idUser, String catName);
}
