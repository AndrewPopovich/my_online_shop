package com.myshop.shopbackend.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;

    private String brand;

    private String description;

    @Column(name = "unit_price")
    private double unitPrice;

    private int quantity;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "supplier_id")
    private int supplierId;

    private int purchases;

    private int views;

}
