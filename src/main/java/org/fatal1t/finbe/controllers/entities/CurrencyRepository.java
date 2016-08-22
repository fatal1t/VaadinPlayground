/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fatal1t
 */
public interface CurrencyRepository extends CrudRepository<Currency, Long>{
    public Currency findById(Long Id);
    public Currency findByIsoCode(String isoCode);
    
}
