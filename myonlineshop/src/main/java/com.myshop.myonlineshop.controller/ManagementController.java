package com.myshop.myonlineshop.controller;

import com.myshop.shopbackend.dto.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView showManageProducts() {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("userClickManageProducts", true);
        mv.addObject("title", "Manage Products");
        Product nProduct = new Product();

        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        mv.addObject("product", nProduct);

        return mv;
    }
}
