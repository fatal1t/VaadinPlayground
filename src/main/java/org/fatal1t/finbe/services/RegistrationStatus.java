/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services;

/**
 *
 * @author fatal1t
 */
public enum RegistrationStatus {

    OK ("Alles guttes"), USED_USERNAME ( "Username is already used") , USED_EMAIL("Used email");
    private final String message;

    private RegistrationStatus(String message) {
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
}
