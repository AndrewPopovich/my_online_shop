package com.myshop.shopbackend.dao;

import com.myshop.shopbackend.dto.Category;

import java.util.List;

public interface CategoryDAO {

    boolean add(Category category);

    boolean update(Category category);

    boolean delete(Category category);

    List<Category> list();

    Category get(int id);
}
