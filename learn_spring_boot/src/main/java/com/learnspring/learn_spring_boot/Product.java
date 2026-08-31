package com.learnspring.learn_spring_boot;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* this is dummy database for product beacause we havn't added the actual database right now */
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product 
{
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) 'this is for 
    // the auto generation of the serial number (product id)'
    private int proId;
    private String proName;
    private int proPrice;

    /*with the help of lombok we don't have to create explictly getter/setter/
    constructor/...(but we need to add the dependency for this from maven repo) */

    private String imageName;
    private String imageType;
    @Lob // because the image byte data is very large (large object)
    private byte[] imageData;
}