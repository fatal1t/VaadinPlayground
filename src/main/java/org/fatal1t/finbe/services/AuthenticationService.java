/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.finbe.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class AuthenticationService {
    private final HashMap<String, Long> tokenList = new HashMap<>();
    public synchronized String createUserToken(Long userId)
    {        
        String token = null;
        try {
            token = generateToken();
            tokenList.putIfAbsent(token, userId);
            return token;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }
    
    ///returns userId
    public synchronized long checkUserToken(String token)
    {
        return tokenList.get(token);
    }
    private String generateToken() throws UnsupportedEncodingException, NoSuchAlgorithmException
    {
        String random = UUID.randomUUID().toString();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(random.getBytes("UTF-8"));
        Encoder enc = Base64.getEncoder();
        return enc.encodeToString(bytes);
    }
    
}
