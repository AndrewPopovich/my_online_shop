package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.UserDAO;
import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean addUser(User user) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().persist(user);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getByEmail(String email) {
        User result = null;

        String selectQuery = "FROM User WHERE email = :email";

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public boolean addAddress(Address address) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().persist(address);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Address getBillingAddress(User user) {
        String selectQuery = "FROM Address WHERE user = :user AND isBilling = :isBilling";

        Address result = null;

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, Address.class)
                    .setParameter("user", user)
                    .setParameter("isBilling", true)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Address> listShippingAddresses(User user) {
        String selectQuery = "FROM Address WHERE user = :user AND isShipping = :isShipping";

        List<Address> result = null;

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery(selectQuery, Address.class)
                    .setParameter("user", user)
                    .setParameter("isShipping", true)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
