package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.ProductDAO;
import com.myshop.shopbackend.dto.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Product product) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().persist(product);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Product product) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().update(product);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean delete(Product product) {
        boolean result = false;

        try {
            product.setActive(false);

            result = update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Product> list() {
        List<Product> result = null;

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery("FROM Product", Product.class)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public Product get(int id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public List<Product> listActiveProducts() {
        String selectActiveProducts = "FROM Product WHERE active = :active";

        Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class);
        query.setParameter("active", true);

        return query.getResultList();
    }

    @Override
    public List<Product> listActiveProductsByCategory(int categoryId) {
        List<Product> result = null;

        String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery(selectActiveProductsByCategory, Product.class)
                    .setParameter("active", true)
                    .setParameter("categoryId", categoryId)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Product> getLatestActiveProducts(int count) {
        List<Product> result = null;

        String selectLatestActiveProducts = "FROM Product WHERE active = :active ORDER BY Id";

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery(selectLatestActiveProducts, Product.class)
                    .setParameter("active", true)
                    .setFirstResult(0)
                    .setMaxResults(count)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
