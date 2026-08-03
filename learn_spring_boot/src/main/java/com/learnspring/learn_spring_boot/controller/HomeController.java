package com.learnspring.learn_spring_boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // this is only for sending the data not the object(page)

public class HomeController 
{
    @RequestMapping("/") 
    public String hello()
    {
        return "Hello";
    }
}
