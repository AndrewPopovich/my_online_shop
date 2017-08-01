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

    /*@BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
    }

    @Test
    public void testAddCategory() {
        category = new Category();
        category.setName("TV");
        category.setDescription("Some description for TV...");
        category.setImageURL("Cat_2.png");

        Assert.assertEquals("Something wrong!", true, categoryDAO.add(category));
    }*/

    /*@Test
    public void testGetCategory() {
        Assert.assertEquals("Something wrong!", "Television", categoryDAO.get(1).getName());
    }

    @Test
    public void testUpdateCategory() {
        category = categoryDAO.get(2);

        category.setName("MacBook");

        Assert.assertEquals("Something wrong!", true, categoryDAO.update(category));
    }

    @Test
    public void testDeleteCategory() {
        category = categoryDAO.get(3);

        Assert.assertEquals("Something wrong!", true, categoryDAO.delete(category));
    }

    @Test
    public void testListCategory() {
        Assert.assertEquals("Something wrong!", 2, categoryDAO.list().size());
    }*/
}
