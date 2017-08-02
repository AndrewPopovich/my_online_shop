package com.myshop.myonlineshop.model;

import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.User;

public class RegisterModel {

    private User user;

    private Address address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
