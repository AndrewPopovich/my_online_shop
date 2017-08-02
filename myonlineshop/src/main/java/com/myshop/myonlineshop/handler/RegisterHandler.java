package com.myshop.myonlineshop.handler;

import com.myshop.myonlineshop.model.RegisterModel;
import com.myshop.shopbackend.dao.UserDAO;
import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    @Autowired
    private UserDAO userDAO;

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String saveAll(RegisterModel model) {
        String transitionValue = "success";

        User user = model.getUser();

        if (user.getRole().equals("USER")) {
            Cart cart = new Cart();
            cart.setUser(user);

            user.setCart(cart);
        }

        userDAO.addUser(user);

        Address billing = model.getBilling();

        billing.setUser(user);
        billing.setBilling(true);

        userDAO.addAddress(billing);

        return transitionValue;
    }
}
