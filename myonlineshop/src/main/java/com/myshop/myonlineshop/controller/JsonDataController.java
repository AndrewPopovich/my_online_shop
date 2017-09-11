package com.myshop.myonlineshop.controller;

import com.myshop.shopbackend.dao.CommentDAO;
import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.Comment;
import com.myshop.shopbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDataController.class);

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        LOGGER.debug("In Json controller getAllProducts run!");
        return productDAO.listActiveProducts();
    }

    @RequestMapping("/admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsForAdmin() {
        LOGGER.debug("In Json controller getAllProductsForAdmin run!");

        return productDAO.list();
    }

    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable int id) {
        return productDAO.listActiveProductsByCategory(id);
    }

    @RequestMapping("/json/data/comments/{productId}/product")
    @ResponseBody
    public List<Comment> getCommentsForProduct(@PathVariable int productId) {
        return commentDAO.listByProductId(productId);
    }
}
