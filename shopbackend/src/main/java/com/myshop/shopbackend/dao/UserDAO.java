package com.myshop.shopbackend.dao;

import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;

public interface UserDAO {

    boolean addUser(User user);

    boolean addAddress(Address address);

    boolean updateCart(Cart cart);
}
