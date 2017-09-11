package com.myshop.shopbackend.daoimpl;

import com.myshop.shopbackend.dao.CommentDAO;
import com.myshop.shopbackend.dto.Comment;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

import static org.junit.Assert.*;

public class CommentDAOImplTest {

    private static AnnotationConfigApplicationContext context;

    private static CommentDAO commentDAO;

    /*@BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.myshop.shopbackend");
        context.refresh();

        commentDAO = (CommentDAO) context.getBean("commentDAO");
    }*/

    /*@Test
    public void testCommentCRUD() {
        Comment comment = new Comment();

        comment.setDescription("This is test comment for product 1 id!");
        comment.setDate(new Date());
        comment.setProductId(2);
        comment.setUserName("Ivanov");

        assertEquals("Something wrong with add method!", true, commentDAO.add(comment));

        comment.setDescription("Test update/delete method!");

        assertEquals("Something wrong with update method!", true, commentDAO.update(comment));

        assertEquals("Something wrong with get method!", "This is test comment for product 1 id!",
                commentDAO.get(1).getDescription());

        assertEquals("Something wrong witn delete method!", true, commentDAO.delete(comment));
    }*/

    /*@Test
    public void testListByProductId() {
        assertEquals("Something wrong with listByProductId method", 2,
                commentDAO.listByProductId(4).size());
    }*/
}