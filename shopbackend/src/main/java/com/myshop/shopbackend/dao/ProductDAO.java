package com.myshop.shopbackend.dao;

import com.myshop.shopbackend.dto.Product;

import java.util.List;

public interface ProductDAO {

    boolean add(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    List<Product> list();

    Product get(int id);


    List<Product> listActiveProducts();

    List<Product> listActiveProductsByCategory(int categoryId);

    List<Product> getLatestActiveProducts(int count);
}
