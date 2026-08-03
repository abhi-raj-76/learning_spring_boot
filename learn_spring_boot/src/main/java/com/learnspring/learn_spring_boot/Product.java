package com.learnspring.learn_spring_boot;

import lombok.AllArgsConstructor;
import lombok.Data;

/* this is dummy database for product beacause we havn't added the actual database right now */
@Data 
@AllArgsConstructor
public class Product 
{
    private int proId;
    private String proName;
    private int proPrice;

    /*with the help of lombok we don't have to create explictly getter/setter/
    constructor/...(but we need to add the dependency for this from maven repo) */
}
