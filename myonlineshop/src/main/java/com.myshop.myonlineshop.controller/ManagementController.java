package com.myshop.myonlineshop.controller;

import com.myshop.myonlineshop.com.myshop.myonlineshop.validator.ProductValidator;
import com.myshop.myonlineshop.util.FileUploadUtility;
import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.Category;
import com.myshop.shopbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("userClickManageProducts", true);
        mv.addObject("title", "Manage Products");

        Product nProduct = new Product();

        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        mv.addObject("product", nProduct);

        if (operation != null && operation.equals("product")) {
            mv.addObject("message", "Product Submitted Successfully!");
        } else {
            if (operation != null && operation.equals("category")) {
                mv.addObject("message", "Category Submitted Successfully!");
            }
        }
        return mv;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
                                          HttpServletRequest request) {

        if (mProduct.getId() == 0) {
            new ProductValidator().validate(mProduct, results);
        } else {
            if (!mProduct.getFile().getOriginalFilename().equals("")) {
                new ProductValidator().validate(mProduct, results);
            }
        }

        if (results.hasErrors()) {
            LOGGER.info("Validator has errors!");

            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", "Manage Products");
            model.addAttribute("message", "Validation failed for Product Submission");

            return "page";
        }

        LOGGER.debug(mProduct.toString());

        if (!mProduct.getFile().getOriginalFilename().equals("")) {
            FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        if (mProduct.getId() == 0) {
            productDAO.add(mProduct);
        } else {
            productDAO.update(mProduct);
        }

        return "redirect:/manage/products?operation=product";
    }

    @RequestMapping(value = "/products/{id}/activation", method = RequestMethod.POST)
    @ResponseBody
    public String handleProductActivation(@PathVariable int id) {

        Product product = productDAO.get(id);

        boolean isActive = product.isActive();

        product.setActive(!isActive);

        productDAO.update(product);

        LOGGER.debug("Activation/Deactivation. Before: " + isActive + "/ After: " + product.isActive());

        return (isActive) ? "You are successfully deactivate the product with id: " + product.getId() + "!" :
                "You are successfully activate the product with id: " + product.getId() + "!";
    }

    @RequestMapping(value = "{id}/product", method = RequestMethod.GET)
    public ModelAndView showEditProduct(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("userClickManageProducts", true);
        mv.addObject("title", "Manage Products");
        Product product = productDAO.get(id);

        mv.addObject("product", product);


        return mv;
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String handleCategorySubmission(@ModelAttribute("category") Category category) {

        categoryDAO.add(category);

        return "redirect:/manage/products?operation=category";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryDAO.list();
    }

    @ModelAttribute("category")
    public Category getCategory() {
        return new Category();
    }
}
