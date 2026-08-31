package com.learnspring.learn_spring_boot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learnspring.learn_spring_boot.Product;
import com.learnspring.learn_spring_boot.repository.ProductRepo;

@Service /*this is also the part of @component so in controller package
we don't have to create the object via on your own */
public class ProductService 
{
    /*List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(101,"iphone",5000),
        new Product(102,"camera",5000)
    ));*/
    @Autowired
    ProductRepo repo;

    public List<Product> getProducts()
    {
        //return products;
        return repo.findAll();
    }

    public Product getProductById(int id) 
    {
        /*return products.stream()
        .filter(p -> p.getProId() == id)
        .findFirst().get(); /*stream API */
        return repo.findById(id).orElse(new Product(id, null, id,null,null,null));
    }

    public Product addProduct(Product prod, MultipartFile imgfile) throws IOException
    {
        //products.add(prod);
        prod.setImageName(imgfile.getOriginalFilename());
        prod.setImageType(imgfile.getContentType());
        prod.setImageData(imgfile.getBytes()); // this will throws the excetption
        return repo.save(prod);
    }

    public void updateProduct(Product prod) 
    {
        repo.save(prod);
    }
    public void deleteProductById(int id)
    {
        repo.deleteById(id);
    }
}
