package com.myshop.shopbackend.dao;

import com.myshop.shopbackend.dto.Comment;

import java.util.List;

public interface CommentDAO {

    boolean add(Comment comment);

    boolean update(Comment comment);

    boolean delete(Comment comment);

    Comment get(int id);

    List<Comment> listByProductId(int productId);
}
