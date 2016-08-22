/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers;

import org.fatal1t.finbe.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class BaseController {
    @Autowired
    private AuthenticationService authService;
    protected Long getUserByToken(String token)
    {
        return authService.checkUserToken(token);
    }
    
}
