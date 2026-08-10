package com.learnspring.learn_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnspring.learn_spring_boot.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>
{

}
