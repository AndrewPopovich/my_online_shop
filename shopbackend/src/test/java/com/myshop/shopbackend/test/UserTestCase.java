package com.myshop.shopbackend.test;

import com.myshop.shopbackend.dao.UserDAO;
import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTestCase {

    private static AnnotationConfigApplicationContext context;

    private static UserDAO userDAO;

    private User user;

    private Address address;

    private Cart cart;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        userDAO = (UserDAO) context.getBean("userDAO");
    }
}
