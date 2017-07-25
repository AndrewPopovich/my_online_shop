package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.dto.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private static List<Category> categories = new ArrayList<>();

    static {
        Category category = new Category();

        category.setId(1);
        category.setName("Television");
        category.setDescription("Some description for television...");
        category.setImageURL("Cat_1.png");

        categories.add(category);

        Category category2 = new Category();

        category2.setId(2);
        category2.setName("Phone");
        category2.setDescription("Some description for phone...");
        category2.setImageURL("Cat_2.png");

        categories.add(category2);

        Category category3 = new Category();

        category3.setId(3);
        category3.setName("Laptop");
        category3.setDescription("Some description for laptop...");
        category3.setImageURL("Cat_3.png");

        categories.add(category3);
    }

    @Override
    public List<Category> list() {
        return categories;
    }
}
