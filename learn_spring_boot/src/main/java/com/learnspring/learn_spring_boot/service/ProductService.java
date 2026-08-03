package com.learnspring.learn_spring_boot.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learnspring.learn_spring_boot.Product;

@Service /*this is also the part of @component so in controller package
we don't have to create the object via on your own */
public class ProductService 
{
    List<Product> products = Arrays.asList(
        new Product(101,"iphone",5000),
        new Product(102,"camera",5000)
    );

    public List<Product> getProducts()
    {
        return products;
    }
}
