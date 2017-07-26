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
        try {
            sessionFactory.getCurrentSession().persist(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try {
            sessionFactory.getCurrentSession().update(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean delete(Product product) {
        try {
            product.setActive(false);

            return update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> list() {
        return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
    }

    @Override
    public Product get(int id) {
        return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
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
        String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";

        Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class);
        query.setParameter("active", true).setParameter("categoryId", categoryId);

        return query.getResultList();
    }

    @Override
    public List<Product> getLatestActiveProducts(int count) {
        String selectLatestActiveProducts = "FROM Product WHERE active = :active ORDER BY Id";

        Query query = sessionFactory.getCurrentSession().createQuery(selectLatestActiveProducts, Product.class);
        query.setParameter("active", true).setFirstResult(0).setMaxResults(count);

        return query.getResultList();
    }
}
