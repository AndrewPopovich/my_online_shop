package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.UserDAO;
import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean addUser(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAddress(Address address) {
        try {
            sessionFactory.getCurrentSession().persist(address);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addCart(Cart cart) {
        try {
            sessionFactory.getCurrentSession().persist(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
