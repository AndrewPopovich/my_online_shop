package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.CategoryDAO;
import com.myshop.shopbackend.dto.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Category category) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().persist(category);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Category category) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().update(category);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Category category) {
        boolean result = false;

        try {
            category.setActive(false);

            result = update(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Category> list() {
        List<Category> result = null;

        String selectActiveCategories = "FROM Category WHERE active = :active";

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery(selectActiveCategories)
                    .setParameter("active", true)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public Category get(int id) {
        return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
    }
}
