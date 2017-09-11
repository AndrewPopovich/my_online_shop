package com.myshop.shopbackend.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @NotBlank
    private String description;

    @Column(name = "file_reference")
    private String fileReference;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "product_id")
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", fileReference='" + fileReference + '\'' +
                ", userName=" + userName +
                ", productId=" + productId +
                '}';
    }
}
