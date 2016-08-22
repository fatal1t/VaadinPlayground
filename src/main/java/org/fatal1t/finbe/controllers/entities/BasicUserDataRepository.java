/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers.entities;

import org.fatal1t.finbe.controllers.entities.interfaces.PublicUser;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fatal1t
 */

public interface BasicUserDataRepository extends CrudRepository<UserData, Long>
{
    public PublicUser findById(Long Id);
    public PublicUser findByUsername(String username);
}