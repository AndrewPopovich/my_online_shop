package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.CartLineDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartLineDAOImplTest {

    private static AnnotationConfigApplicationContext context;

    private static CartLineDAO cartLineDAO;

    @BeforeClass
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void list() throws Exception {
    }

}