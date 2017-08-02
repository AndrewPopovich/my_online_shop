package com.myshop.myonlineshop.handler;

import com.myshop.myonlineshop.model.RegisterModel;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    public RegisterModel init() {
        return new RegisterModel();
    }
}
