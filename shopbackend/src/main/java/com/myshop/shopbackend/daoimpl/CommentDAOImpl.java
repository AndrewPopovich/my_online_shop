package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.CommentDAO;
import com.myshop.shopbackend.dto.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("commentDAO")
@Transactional
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Comment comment) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().persist(comment);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Comment comment) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession().update(comment);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Comment comment) {
        boolean result = false;

        try {
            sessionFactory.getCurrentSession()
                    .delete(comment);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Comment get(int id) {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Override
    public List<Comment> listByProductId(int productId) {
        List<Comment> result = null;

        String query = "FROM Comment WHERE productId = :productId";

        try {
            result = sessionFactory.getCurrentSession()
                    .createQuery(query, Comment.class)
                    .setParameter("productId", productId)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
