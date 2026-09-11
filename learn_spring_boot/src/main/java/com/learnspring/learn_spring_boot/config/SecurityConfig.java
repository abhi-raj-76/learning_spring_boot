package com.learnspring.learn_spring_boot.config;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learnspring.learn_spring_boot.service.UserService;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    /*@Autowired
    private UserService userService;*/
    //removed above code for cyclic problem 

    @Autowired
    JWTFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
            .requestMatchers("/h2-console/**").permitAll() // this will allow h2 console without authorization
            .requestMatchers("/register").permitAll() // this will allow registering new user without authorization (because this dosen't make any sense)
            .requestMatchers("/userlogin").permitAll()
            //this api is from UserController POSTMapping
            .anyRequest().authenticated());
        // with this just above code, no one able to access the page without authentication
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        // this above code is for h2 console because h2 console runs inside a fame
        // and spring security's default headers can block it.

        http.formLogin(Customizer.withDefaults()); // enabled form UI
        http.httpBasic(Customizer.withDefaults()); // enabled access through Post man / API

        // now adding validation code for JWT
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    /*@Bean
    // this is for user validation from database
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User
            .withDefaultPasswordEncoder()
            .username("raj")
            .password("raj123")
            .roles("USER")
            .build();
        
        UserDetails user2 = User
            .withDefaultPasswordEncoder()
            .username("abhi")
            .password("abhi123")
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }*/ //above code wil only work for dummy username and password which is not from the database

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService) {

        DaoAuthenticationProvider provider = 
            new DaoAuthenticationProvider(userService); // this is the user service layer, created by me

        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        // this will only work for plain text password
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }
    // this is for JWT
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }

}
