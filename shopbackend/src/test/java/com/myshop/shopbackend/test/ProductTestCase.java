package com.myshop.shopbackend.test;

import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductTestCase {

    private static AnnotationConfigApplicationContext context;

    private static ProductDAO productDAO;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        productDAO = (ProductDAO) context.getBean("productDAO");
    }

    /*@Test
    public void testCRUDProduct() {
        Product product = new Product();

        product.setName("Samsung S8500");
        product.setBrand("Samsung");
        product.setDescription("This is some description for s5000");
        product.setUnitPrice(700);
        product.setCategoryId(3);
        product.setSupplierId(3);

        Assert.assertEquals("Something wrong!(add)", true, productDAO.add(product));

        product = productDAO.get(2);
        product.setName("Samsung Galaxy s8");
        Assert.assertEquals("Something wrong!(update)", true, productDAO.update(product));

        Assert.assertEquals("Something wrong!(delete)", true, productDAO.delete(product));

        Assert.assertEquals("Something wrong!(list)", 9, productDAO.list().size());
    }*/

    @Test
    public void testListActiveProducts() {
        /*Product product = productDAO.get(1);
        product.setQuantity(0);
        Assert.assertEquals(true, productDAO.update(product));*/
        Assert.assertEquals("Something wrong!(listActive)", 5, productDAO.listActiveProducts().size());
    }
}
