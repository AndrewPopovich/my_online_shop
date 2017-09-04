package com.myshop.myonlineshop.controller;

import com.myshop.myonlineshop.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Value("${cart.message.updateSuccess}")
    private String updateSuccess;

    @Value("${cart.message.addSuccess}")
    private String addSuccess;

    @Value("${cart.message.deleteSuccess}")
    private String deleteSuccess;

    @Value("${cart.message.error}")
    private String error;

    @Value("${cart.title}")
    private String title;

    @Value("${page.jsp.page}")
    private String page;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService service;

    @RequestMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
        ModelAndView mv = new ModelAndView(page);

        LOGGER.debug("In showCart method! Result = " + result);

        if (result != null) {
            switch (result) {
                case "updated":
                    mv.addObject("message", updateSuccess);
                    break;
                case "added":
                    mv.addObject("message", addSuccess);
                    break;
                case "deleted":
                    mv.addObject("message", deleteSuccess);
                    break;
                case "error":
                    mv.addObject("message", error);
                    break;
            }
        }
        mv.addObject("title", title);
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
