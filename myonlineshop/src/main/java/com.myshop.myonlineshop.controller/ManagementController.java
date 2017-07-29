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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        }
        return mv;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
                                          HttpServletRequest request) {

        new ProductValidator().validate(mProduct, results);

        if (results.hasErrors()) {
            LOGGER.warn("Validator has errors!");

            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", "Manage Products");
            model.addAttribute("message", "Validation failed for Product Submission");

            return "page";
        }

        LOGGER.info(mProduct.toString());

        productDAO.add(mProduct);

        if(!mProduct.getFile().getOriginalFilename().equals("")) {
            FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        return "redirect:/manage/products?operation=product";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryDAO.list();
    }
}