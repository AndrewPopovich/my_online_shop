package com.myshop.myonlineshop.controller;

import com.myshop.myonlineshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @RequestMapping("/show")
    public ModelAndView showCart() {
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title", "User Cart");
        mv.addObject("userClickShowCart", true);
        mv.addObject("cartLines", service.getCartLines());

        return mv;
    }
}
