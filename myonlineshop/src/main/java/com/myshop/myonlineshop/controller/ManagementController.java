package com.myshop.myonlineshop.controller;

import com.myshop.myonlineshop.util.FileUploadUtility;
import com.myshop.myonlineshop.validator.ProductValidator;
import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.Category;
import com.myshop.shopbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    @Value("${manage.title.manageProducts}")
    private String manageProducts;

    @Value("${manage.message.productSubmmitSuccess}")
    private String productSubmmitSuccess;

    @Value("${manage.message.categorySubmmitSuccess}")
    private String categorySubmmitSuccess;

    @Value("${manage.message.validatorError}")
    private String validatorError;

    @Value("${page.jsp.page}")
    private String page;

    @Value("${manage.message.deactivate}")
    private String deactivate;

    @Value("${manage.message.activate}")
    private String activate;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("userClickManageProducts", true);
        mv.addObject("title", manageProducts);

        Product nProduct = new Product();

        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        mv.addObject("product", nProduct);

        if (operation != null && operation.equals("product")) {
            mv.addObject("message", productSubmmitSuccess);
        } else {
            if (operation != null && operation.equals("category")) {
                mv.addObject("message", categorySubmmitSuccess);
            }
        }
        return mv;
    }

    private boolean checkFilesIsEmpty(List<MultipartFile> files) {
        int count = 0;

        for (MultipartFile file : files) {
            if (file.getSize() != 0) {
                count++;
            }
        }
        return count == 0;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
                                          Model model, HttpServletRequest request) {
        if (mProduct.getId() == 0) {
            new ProductValidator().validate(mProduct, results);
        } else {
            if (!checkFilesIsEmpty(mProduct.getFiles())) {
                new ProductValidator().validate(mProduct, results);
            }
        }
        LOGGER.debug(mProduct.toString() + "mProduct file = " + mProduct.getFiles());

        if (results.hasErrors()) {
            LOGGER.info("Validator has errors!" + results);

            model.addAttribute("userClickManageProducts", true);
            model.addAttribute("title", manageProducts);
            model.addAttribute("message", validatorError);

            return page;
        }

        if (checkFilesIsEmpty(mProduct.getFiles())) {
            FileUploadUtility.uploadFile(request, mProduct.getFiles(), mProduct.getCode());
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

        return (isActive) ? deactivate + product.getId() + "!" : activate + product.getId() + "!";
    }

    @RequestMapping(value = "{id}/product", method = RequestMethod.GET)
    public ModelAndView showEditProduct(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("userClickManageProducts", true);
        mv.addObject("title", manageProducts);
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
