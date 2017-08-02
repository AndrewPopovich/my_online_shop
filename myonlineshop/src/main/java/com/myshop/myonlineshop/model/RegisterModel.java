package com.myshop.myonlineshop.model;

import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.User;

import java.io.Serializable;

public class RegisterModel implements Serializable {

    private static final long serialVersionUID = -1802670928616832187L;

    private User user;

    private Address billing;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }
}
