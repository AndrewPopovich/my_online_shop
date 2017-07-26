package com.myshop.shopbackend.test;

import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.dto.Category;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CategoryTestCase {

    private static AnnotationConfigApplicationContext context;

    private static CategoryDAO categoryDAO;

    private Category category;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
    }

    @Test
    public void testAddCategory() {
        category = new Category();
        category.setName("Television");
        category.setDescription("Some description for television...");
        category.setImageURL("Cat_1.png");

        Assert.assertEquals("Successfully added category!", true, categoryDAO.add(category));
    }
}
