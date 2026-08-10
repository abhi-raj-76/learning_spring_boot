package com.learnspring.learn_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     @RequestMapping("/product/{id}")
     public Product getProductById(@PathVariable int id)
     {
        return service.getProductById(id);
     }

     //@RequestMapping("/products") 
     @PostMapping("/products")

     /* for the same url how can it identify which request is coming so 
     we have two options 1. change the url for each diff request 2. use 
     specialized annotations 
     for get - GetMapping, for post - PostMapping .... */
     public void addProduct(@RequestBody Product prod)
     {
        service.addProduct(prod);
     }

     @PutMapping("/product")
     public void updateProduct(@RequestBody Product prod)
     {
        service.updateProduct(prod);
     }
}
