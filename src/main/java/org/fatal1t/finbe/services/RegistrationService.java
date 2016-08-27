/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services;

import org.fatal1t.finbe.controllers.entities.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class RegistrationService {
    @Autowired
    private UserDataRepository userRepository;
    
    public RegistrationStatus register(String username, String email, String password)
    {
        if(userRepository.findByUsername(username) != null )
        {
            return RegistrationStatus.USED_USERNAME;
        }
        if(userRepository.findByEmail(email) != null)
        {
            return RegistrationStatus.USED_EMAIL;
        }
        // do security here and for loginService
        
        return RegistrationStatus.OK;
    }
    
    
    
}
