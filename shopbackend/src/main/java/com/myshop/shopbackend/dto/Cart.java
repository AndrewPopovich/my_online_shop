package com.myshop.shopbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = 1550049260398824936L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    @Column(name = "grand_total")
    private double grandTotal;

    @Column(name = "cart_lines")
    private int cartLines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getCartLines() {
        return cartLines;
    }

    public void setCartLines(int cartLines) {
        this.cartLines = cartLines;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", grandTotal=" + grandTotal +
                ", cartLines=" + cartLines +
                '}';
    }
}
