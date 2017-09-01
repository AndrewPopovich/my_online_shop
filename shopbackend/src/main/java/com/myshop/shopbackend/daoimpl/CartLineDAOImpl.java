package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.CartLineDAO;
import com.myshop.shopbackend.dto.CartLine;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CartLine get(int id) {
        return sessionFactory.getCurrentSession().get(CartLine.class, id);
    }

    @Override
    public boolean add(CartLine cartLine) {
        return false;
    }

    @Override
    public boolean update(CartLine cartLine) {
        return false;
    }

    @Override
    public boolean delete(CartLine cartLine) {
        return false;
    }

    @Override
    public List<CartLine> list(int cartId) {
        return null;
    }
}
