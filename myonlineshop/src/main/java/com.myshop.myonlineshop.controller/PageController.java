package com.myshop.myonlineshop.controller;

import com.myshop.myonlineshop.exception.ProductNotFoundException;
import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.Category;
import com.myshop.shopbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("page");

        LOGGER.debug("PageController index");

        mv.addObject("categories", categoryDAO.list());
        mv.addObject("title", "Home");
        mv.addObject("userClickHome", true);

        return mv;
    }

    @RequestMapping(value = {"/about"})
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "About as");
        mv.addObject("userClickAbout", true);

        return mv;
    }

    @RequestMapping(value = {"/contact"})
    public ModelAndView contact() {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "Our contact");
        mv.addObject("userClickContact", true);

        return mv;
    }

    @RequestMapping(value = "/show/all/products")
    public ModelAndView showAllProducts() {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "All products");
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("userClickAllProducts", true);

        return mv;
    }

    @RequestMapping(value = "/show/category/{id}/products")
    public ModelAndView showCategoriesProducts(@PathVariable(value = "id") int id) {
        ModelAndView mv = new ModelAndView("page");

        Category category = categoryDAO.get(id);

        mv.addObject("title", category.getName());
        mv.addObject("category", category);
        mv.addObject("categories", categoryDAO.list());
        mv.addObject("userClickCategoryProducts", true);

        return mv;
    }

    @RequestMapping(value = "/show/{id}/product")
    public ModelAndView showSingleProduct(@PathVariable(value = "id") int id) throws ProductNotFoundException {
        ModelAndView mv = new ModelAndView("page");

        Product product = productDAO.get(id);

        if (product == null) throw new ProductNotFoundException();

        product.setViews(product.getViews() + 1);
        productDAO.update(product);

        mv.addObject("title", product.getName());
        mv.addObject("product", product);
        mv.addObject("userClickShowProduct", true);

        return mv;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(name = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");

        if (error != null) {
            mv.addObject("message", "Invalid Username or Password!");
        }
        mv.addObject("title", "Login");
        return mv;
    }
}