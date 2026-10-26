package com.darkweb.school.managent.service;

import com.darkweb.school.managent.model.User;
import com.darkweb.school.managent.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        System.out.println("USERNAME: " + user.getUsername());
        System.out.println("PASSWORD FROM DB: " + user.getPassword());

                return new MyDetails(user);
    }


    public User registered(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

}
