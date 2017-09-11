package com.myshop.shopbackend.dao;

import com.myshop.shopbackend.dto.Comment;

import java.util.List;

public interface CommentDAO {

    boolean add(Comment product);

    boolean update(Comment product);

    boolean delete(Comment product);

    Comment get(int id);

    List<Comment> listByProductId(int productId);
}
