package com.learnspring.learn_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnspring.learn_spring_boot.Users;


@Repository

public interface UserRepo extends JpaRepository<Users,Integer>{

    Users findByUsername(String usernmame);
}
