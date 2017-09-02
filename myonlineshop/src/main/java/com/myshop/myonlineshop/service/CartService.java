package com.myshop.myonlineshop.service;

import com.myshop.myonlineshop.model.UserModel;
import com.myshop.shopbackend.dao.CartLineDAO;
import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.CartLine;
import com.myshop.shopbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("cartService")
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private ProductDAO productDAO;

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

    public String updateCartLine(int cartLineId, int count) {
        CartLine cartLine = cartLineDAO.get(cartLineId);

        LOGGER.debug("In updateCartLine method! CartLineId = " + cartLineId);

        if (cartLine == null) {
            return "result=error";
        } else {
            Product product = cartLine.getProduct();
            double oldTotal = cartLine.getTotal();

            if (product.getQuantity() <= count) {
                count = product.getQuantity();
            }

            cartLine.setProductCount(count);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setTotal(product.getUnitPrice() * count);

            cartLineDAO.update(cartLine);

            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());

            cartLineDAO.updateCart(cart);

            return "result=updated";
        }

    }

    public String deleteCartLine(int cartLineId) {
        CartLine cartLine = cartLineDAO.get(cartLineId);

        if (cartLine == null) {
            return "result=error";
        } else {
            Cart cart = this.getCart();
            cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
            cart.setCartLines(cart.getCartLines() - 1);
            cartLineDAO.updateCart(cart);

            cartLineDAO.delete(cartLine);

            return "result=deleted";
        }
    }

    public String addCartLine(int productId) {
        Cart cart = this.getCart();
        CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);

        if (cartLine == null) {
            cartLine = new CartLine();

            Product product = productDAO.get(productId);

            cartLine.setCartId(cart.getId());
            cartLine.setProduct(product);
            cartLine.setBuyingPrice(product.getUnitPrice());
            cartLine.setProductCount(1);
            cartLine.setTotal(product.getUnitPrice());
            cartLine.setAvailable(true);

            cartLineDAO.add(cartLine);

            cart.setCartLines(cart.getCartLines() + 1);
            cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());

            cartLineDAO.updateCart(cart);
        } else {
            updateCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
        }
        return "result=added";
    }
}
