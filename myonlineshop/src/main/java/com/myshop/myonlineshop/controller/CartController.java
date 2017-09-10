package com.myshop.myonlineshop.controller;

import com.myshop.myonlineshop.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService service;

    @RequestMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
        ModelAndView mv = new ModelAndView("page");

        LOGGER.debug("In showCart method! Result = " + result);

        if (result != null) {
            switch (result) {
                case "updated":
                    mv.addObject("message", "CartLine has been updated successfully!");
                    break;
                case "added":
                    mv.addObject("message", "CartLine has been added successfully!");
                    break;
                case "deleted":
                    mv.addObject("message", "CartLine has been deleted successfully!");
                    break;
                case "error":
                    mv.addObject("message", "Something was wrong!");
                    break;
            }
        }

        mv.addObject("title", "User Cart");
        mv.addObject("userClickShowCart", true);
        mv.addObject("cartLines", service.getCartLines());

        return mv;
    }

    @RequestMapping("/{cartLineId}/update")
    public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {
        String response = service.updateCartLine(cartLineId, count);

        LOGGER.debug("In updateCart method! Response = " + response);

        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/{cartLineId}/delete")
    public String deleteCart(@PathVariable int cartLineId) {
        String response = service.deleteCartLine(cartLineId);

        return "redirect:/cart/show?" + response;
    }

    @RequestMapping("/add/{productId}/product")
    public String addProductInCart(@PathVariable int productId) {
        String response = service.addCartLine(productId);

        return "redirect:/cart/show?" + response;
    }
}