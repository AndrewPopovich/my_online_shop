package com.myshop.shopbackend.dao;

import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;

import java.util.List;

public interface UserDAO {

    boolean addUser(User user);

    User getByEmail(String email);

    boolean addAddress(Address address);

    Address getBillingAddress(User user);

    List<Address> listShippingAddresses(User user);

    boolean updateCart(Cart cart);
}
