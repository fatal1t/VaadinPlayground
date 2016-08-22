/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services.loginservice;

import java.util.List;
import org.fatal1t.finbe.controllers.entities.UserData;
import org.fatal1t.finbe.controllers.entities.UserDataRepository;
import org.fatal1t.finbe.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class LoginService {
    @Autowired
    private UserDataRepository entityRepository;
    @Autowired
    private AuthenticationService authService;
    public LoginResp login( LoginReq request)
    {
        List<UserData> userList = entityRepository.findByUsername(request.getUsername());
        String token = authService.createUserToken(userList.get(0).getId());
        return new LoginResp(userList.get(0), token);
    }
    public String login(String username, String hashedPassword)
    {
        List<UserData> userList = entityRepository.findByUsername(username);
        Long index = 0L;
        for(UserData e : userList)
        {
            if(e.getPassword() == null ? hashedPassword == null : e.getPassword().equals(hashedPassword))
            {
                index = e.getId();
            }
        }
        if(index != 0L){
            String token = authService.createUserToken(index);
            return token;
        }
        else
            return null;
    }
}
