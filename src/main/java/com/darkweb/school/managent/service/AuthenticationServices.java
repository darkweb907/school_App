package com.darkweb.school.managent.service;

import com.darkweb.school.managent.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {
    @Autowired
    private JjwtService jjwtService;

    @Autowired
    private AuthenticationManager manages;
    public String verify(User user) {
        Authentication auth = manages.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword()));

        if(auth.isAuthenticated())
            return jjwtService.generate(user) ;
        else
            return "fail to get authenticated  ";
    }
}
