package com.myshop.shopbackend.test;

import com.myshop.shopbackend.dao.UserDAO;
import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
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

    /*@Test
    public void testUserAdd() {
        user = new User();
        user.setFirstName("Petya");
        user.setLastName("Petrov");
        user.setContactNumber("88005553535");
        user.setEmail("petrov@gmail.com");
        user.setPassword("password");
        user.setRole("USER");

        Assert.assertEquals("Failed to add user!", true, userDAO.addUser(user));

        address = new Address();
        address.setAddressLineOne("Lenina st.");
        address.setAddressLineTwo("54a");
        address.setCity("Dnipro");
        address.setCountry("Ukraine");
        address.setState("Dnipropetrovska obl.");
        address.setPostalCode("51000");
        address.setBilling(true);
        address.setUserId(user.getId());

        Assert.assertEquals("Failed to add address!", true, userDAO.addAddress(address));

        if (user.getRole().equals("USER")) {
            cart = new Cart();
            cart.setCartLines(1);
            cart.setGrandTotal(1222);
            cart.setUser(user);

            Assert.assertEquals("Failed to add cart!", true, userDAO.addCart(cart));

            address = new Address();
            address.setAddressLineOne("Cantral st.");
            address.setAddressLineTwo("4a");
            address.setCity("Dnipro");
            address.setCountry("Ukraine");
            address.setState("Dnipropetrovska obl.");
            address.setPostalCode("51000");
            address.setShipping(true);
            address.setUserId(user.getId());

            Assert.assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));

        }
    }*/

    /*@Test
    public void testUpdateCart() {
        user = userDAO.getByEmail("petrov@gmail.com");

        cart = user.getCart();

        cart.setGrandTotal(122);
        cart.setCartLines(2);
        Assert.assertEquals("Failed to update a cart", true, userDAO.updateCart(cart));
    }*/

    /*@Test
    public void testAddAddress() {
        user = new User();
        user.setFirstName("Petya");
        user.setLastName("Petrov");
        user.setContactNumber("88005553535");
        user.setEmail("petrov@gmail.com");
        user.setPassword("password");
        user.setRole("USER");

        Assert.assertEquals("Failed to add user!", true, userDAO.addUser(user));

        address = new Address();
        address.setAddressLineOne("Lenina st.");
        address.setAddressLineTwo("54a");
        address.setCity("Dnipro");
        address.setCountry("Ukraine");
        address.setState("Dnipropetrovska obl.");
        address.setPostalCode("51000");
        address.setBilling(true);
        address.setUser(user);

        Assert.assertEquals("Failed to add address!", true, userDAO.addAddress(address));

        address = new Address();
        address.setAddressLineOne("Cantral st.");
        address.setAddressLineTwo("4a");
        address.setCity("Dnipro");
        address.setCountry("Ukraine");
        address.setState("Dnipropetrovska obl.");
        address.setPostalCode("51000");
        address.setShipping(true);
        address.setUser(user);

        Assert.assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));
    }*/

    /*@Test
    public void testAddAddress() {
        user = userDAO.getByEmail("petrov@gmail.com");

        address = new Address();
        address.setAddressLineOne("Central st.");
        address.setAddressLineTwo("4a");
        address.setCity("Kharkiv");
        address.setCountry("Ukraine");
        address.setState("Dnipropetrovska obl.");
        address.setPostalCode("51000");
        address.setShipping(true);
        address.setUser(user);

        Assert.assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));
    }*/

    @Test
    public void testAddAddresses() {
        user = userDAO.getByEmail("petrov@gmail.com");

        Assert.assertEquals("Failed to get billing address!", 1, userDAO.getBillingAddress(user).getId());

        Assert.assertEquals("Failed to get shipping addresses", 2, userDAO.listShippingAddresses(user).size());
    }
}
