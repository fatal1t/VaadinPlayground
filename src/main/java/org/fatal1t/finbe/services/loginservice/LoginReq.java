/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services.loginservice;

/**
 *
 * @author fatal1t
 */
public class LoginReq {
    private String username;
    private String password;    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginReq() {
    }
    
}
