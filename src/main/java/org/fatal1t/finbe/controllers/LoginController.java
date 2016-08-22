/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.fatal1t.finbe.services.loginservice.LoginReq;
import org.fatal1t.finbe.services.loginservice.LoginResp;
import org.fatal1t.finbe.services.loginservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService service;
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResp login(@RequestBody LoginReq request, HttpServletResponse response)
    {
    
    LoginResp loginResposne =  service.login(request);
    
    response.addCookie(new Cookie("sessionId", loginResposne.getToken()));
    return loginResposne;
    }
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public boolean logout(@CookieValue(value = "sessionId") String token, @RequestBody LoginReq request,  HttpServletResponse response)
    {
    Cookie c = new Cookie("sessionId", null);
    c.setMaxAge(0);
    response.addCookie(c);
    System.out.println("On logout: " +token);
    return true;
    }
}
    