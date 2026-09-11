package com.learnspring.learn_spring_boot.service;

import com.learnspring.learn_spring_boot.model.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnspring.learn_spring_boot.Users;
import com.learnspring.learn_spring_boot.repository.UserRepo;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    @Autowired 
    JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);
        if(user == null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users user) {

        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user,AuthenticationManager authManager) {
        Authentication auth = authManager.
        authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(auth.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "fail to generate token";

    }
}
