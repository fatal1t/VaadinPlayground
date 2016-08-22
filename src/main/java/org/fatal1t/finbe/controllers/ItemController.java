/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.fatal1t.finbe.services.ItemsService;
import org.fatal1t.finbe.services.entities.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ItemController extends BaseController {
    @Autowired
    private ItemsService service;
    
    @RequestMapping(path = "/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserItem getItem(@CookieValue(value = "sessionId") String token, @PathVariable(value = "id") Long id,  HttpServletResponse response)
    {
        Long userid = getUserByToken(token);
        return service.getItemById(userid, id);
    
    }
    @RequestMapping(path = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<UserItem> getItems(@CookieValue(value = "sessionId") String token, HttpServletResponse response)
    {
        //Long userid = getUserByToken(token);
        System.out.println("Service Items user id " + 1L);
        return service.getAllItems(1L);
    }
    @RequestMapping(path = "/item", method = RequestMethod.POST)
    @ResponseBody
    public UserItem setItem(@CookieValue(value = "sessionId") String token, @RequestBody UserItem request, HttpServletResponse response)
    {
        Long userId = getUserByToken(token);
        //request.setIdUser(userId);
        this.service.setItem(request, userId);
        return request;
    }
}
