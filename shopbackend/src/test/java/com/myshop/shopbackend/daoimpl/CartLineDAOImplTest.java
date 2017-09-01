package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.CartLineDAO;
import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.CartLine;
import com.myshop.shopbackend.dto.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartLineDAOImplTest {

    private static AnnotationConfigApplicationContext context;

    private static CartLineDAO cartLineDAO;

    private static ProductDAO productDAO;

    @BeforeClass
    public static void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
        productDAO = (ProductDAO) context.getBean("productDAO");
    }

    /*@Test
    public void testCRUDCartLine() {
        CartLine cartLine = new CartLine();

        Product product = productDAO.get(1);

        cartLine.setCartId(1);
        cartLine.setProduct(product);
        cartLine.setBuyingPrice(product.getUnitPrice());
        cartLine.setProductCount(1);
        cartLine.setTotal(product.getUnitPrice());

        Assert.assertEquals("Something wrong with add method", true, cartLineDAO.add(cartLine));

        Assert.assertEquals("Something wrong with get method", true,
                cartLineDAO.get(cartLine.getId()).getBuyingPrice() == cartLine.getBuyingPrice());

        cartLine.setBuyingPrice(0);
        Assert.assertEquals("Something wrong with update method", true, cartLineDAO.update(cartLine));

        Assert.assertEquals("Something wrong with delete method", true, cartLineDAO.delete(cartLine));
    }*/

    /*@Test
    public void testListCartLine() {
        CartLine cartLine = new CartLine();
        CartLine secondCartLine = new CartLine();

        Product product = productDAO.get(2);

        cartLine.setCartId(1);
        cartLine.setProduct(product);
        cartLine.setBuyingPrice(product.getUnitPrice());
        cartLine.setProductCount(1);
        cartLine.setTotal(product.getUnitPrice());

        cartLineDAO.add(cartLine);

        secondCartLine.setCartId(1);
        secondCartLine.setProduct(product);
        secondCartLine.setBuyingPrice(product.getUnitPrice());
        secondCartLine.setProductCount(1);
        secondCartLine.setTotal(1);

        cartLineDAO.add(secondCartLine);

        Assert.assertEquals("Something wrong with list method", 8, cartLineDAO.list(1).size());
    }*/

    /*@Test
    public void testAvailableListCartLine() {
        Assert.assertEquals("Something wrong with list available method", 3, cartLineDAO.listAvailable(1).size());
    }*/

    /*@Test
    public void testGetByCartAndProduct() {
        Assert.assertEquals("Something wrong with getByCartAndProduct mathod", 3,
                cartLineDAO.getByCartAndProduct(1, 1).getId());
    }*/



}