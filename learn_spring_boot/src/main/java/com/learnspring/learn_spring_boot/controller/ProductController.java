package com.learnspring.learn_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learnspring.learn_spring_boot.Product;
import com.learnspring.learn_spring_boot.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
     @PostMapping("/product")

     /* for the same url how can it identify which request is coming so 
     we have two options 1. change the url for each diff request 2. use 
     specialized annotations 
     for get - GetMapping, for post - PostMapping .... */
     /* this below code not work for image handling
     public void addProduct(@RequestBody Product prod)
     {
        service.addProduct(prod);
     }*/
    public ResponseEntity<?> addProduct(@RequestPart("prod") Product prod,@RequestPart("proImage") MultipartFile imgfile)
    {
      /*this 'prod' and 'proImage' should need to match with frontend, where i am sending the data */
      try
      {
         Product prod1 = service.addProduct(prod,imgfile);
         return new ResponseEntity<>(prod1,HttpStatus.CREATED);
      }
      catch(Exception e)
      {
         return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

     @PutMapping("/product")
     public void updateProduct(@RequestBody Product prod)
     {
        service.updateProduct(prod);
     }

     @GetMapping("/product/{id}/image")
     public ResponseEntity<byte[]> getProductImageById(@PathVariable int prodId)
     {
      Product prod = service.getProductById(prodId);
      byte[] imgfile = prod.getImageData();

      return ResponseEntity.ok().body(imgfile);
     }
}
