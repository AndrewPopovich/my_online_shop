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
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
        productDAO = (ProductDAO) context.getBean("productDAO");
    }

    @Test
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
                cartLineDAO.get(cartLine.getId()).equals(cartLine));

        cartLine.setBuyingPrice(0);
        Assert.assertEquals("Something wrong with update method", true, cartLineDAO.update(cartLine));

        Assert.assertEquals("Something wrong with delete method", true, cartLineDAO.delete(cartLine));
    }

}