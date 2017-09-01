package com.myshop.myonlineshop.service;

import com.myshop.myonlineshop.model.UserModel;
import com.myshop.shopbackend.dao.CartLineDAO;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.CartLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("cartService")
public class CartService {

    @Autowired
    private CartLineDAO cartLineDAO;

    @Autowired
    private HttpSession session;

    private Cart getCart() {
        return ((UserModel) session.getAttribute("userModel")).getCart();
    }

    public List<CartLine> getCartLines() {
        Cart cart = getCart();

        return cartLineDAO.list(cart.getId());
    }
}
