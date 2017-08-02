package com.myshop.myonlineshop.handler;

import com.myshop.myonlineshop.model.RegisterModel;
import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }
}
