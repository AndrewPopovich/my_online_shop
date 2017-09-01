package com.myshop.myonlineshop.service;

import com.myshop.shopbackend.dao.CartLineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartService {

    @Autowired
    private CartLineDAO cartLineDAO;
}
