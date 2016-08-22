/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services.loginservice;

import org.fatal1t.finbe.controllers.entities.UserData;

/**
 *
 * @author fatal1t
 */
public class LoginResp {
    private String token;
    private final String username;
    private final String email;

    public LoginResp(String username, String email, String token) {
        this.username = username;
        this.email = email;
        this.token = token;
    }   
    
    public LoginResp(UserData data)
    {
        this.username = data.getUsername();
        this.email = data.getEmail();
    }
    public LoginResp(UserData data, String token)
    {
        this.username = data.getUsername();
        this.email = data.getEmail();
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }  
    public void setToken(String token)
    {
        this.token = token;
    }
    
}
