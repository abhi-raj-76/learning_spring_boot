package com.learnspring.learn_spring_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        // with this just above code, no one able to access the page without authentication
        http.formLogin(Customizer.withDefaults()); // enabled form UI
        http.httpBasic(Customizer.withDefaults()); // enabled access through Post man / API

        return http.build();
    }
    @Bean
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
    }

}
