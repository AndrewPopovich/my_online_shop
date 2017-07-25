package com.myshop.myonlineshop.controller;

import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class PageController {

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("page");

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
}