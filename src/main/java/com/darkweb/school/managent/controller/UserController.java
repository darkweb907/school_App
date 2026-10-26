package com.darkweb.school.managent.controller;

import com.darkweb.school.managent.model.User;
import com.darkweb.school.managent.service.AuthenticationServices;
import com.darkweb.school.managent.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {
    @Autowired
    MyUserDetailsService service;

    @Autowired
    private AuthenticationServices authservices;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user ){
        return new ResponseEntity<>(service.registered(user) , HttpStatus.CREATED);
    }

   @PostMapping("/login")
   public String login(@RequestBody User user){
        return authservices.verify(user);
   }
}
