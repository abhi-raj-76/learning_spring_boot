package com.learnspring.learn_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnspring.learn_spring_boot.Product;
import com.learnspring.learn_spring_boot.service.ProductService;

@RestController
public class ProductController 
{
     @Autowired
     ProductService service;
     @RequestMapping("/products")
     public List<Product> getProducts()
     {
        return service.getProducts();
     }
}
