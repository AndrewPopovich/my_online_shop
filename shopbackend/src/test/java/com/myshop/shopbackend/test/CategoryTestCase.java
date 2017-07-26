package com.myshop.shopbackend.test;

import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.daoimpl.CategoryDAOImpl;
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

    /*@Test
    public void testAddCategory() {
        category = new Category();
        category.setName("Phone");
        category.setDescription("Some description for phone...");
        category.setImageURL("Cat_2.png");

        Assert.assertEquals("Successfully added category!", true, categoryDAO.add(category));
    }*/

    /*@Test
    public void testGetCategory() {
        Assert.assertEquals("Successfully get category!", "Television", categoryDAO.get(1).getName());
    }*/

    @Test
    public void testUpdateCategory() {
        category = categoryDAO.get(2);

        category.setName("MacBook");

        Assert.assertEquals("Successfully update category!", true, categoryDAO.update(category));
    }

    @Test
    public void testDeleteCategory() {
        category = categoryDAO.get(2);

        Assert.assertEquals("Successfully delete category!", true, categoryDAO.delete(category));
    }
}
