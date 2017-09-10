package com.myshop.myonlineshop.controller;

import com.myshop.myonlineshop.model.UserModel;
import com.myshop.shopbackend.dao.UserDAO;
import com.myshop.shopbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserDAO userDAO;

    @ModelAttribute("userModel")
    public UserModel getUserModel() {
        if (httpSession.getAttribute("userModel") == null) {
            return createUserModel();
        }
        return (UserModel) httpSession.getAttribute("userModel");
    }

    private UserModel createUserModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserModel userModel = null;

        User user = userDAO.getByEmail(authentication.getName());

        if (user != null) {
            userModel = new UserModel();

            userModel.setId(user.getId());
            userModel.setEmail(user.getEmail());
            userModel.setRole(user.getRole());
            userModel.setFullName(user.getFirstName() + " " + user.getLastName());

            if (userModel.getRole().equals("USER")) {
                userModel.setCart(user.getCart());
            }

            httpSession.setAttribute("userModel", userModel);
        }
        return userModel;
    }
}
