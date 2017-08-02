package com.myshop.shopbackend.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 5582179651420194758L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    @Column(name = "address_line_one")
    @NotBlank(message = "Please enter address line one")
    private String addressLineOne;

    @Column(name = "address_line_two")
    @NotBlank(message = "Please enter address line two!")
    private String addressLineTwo;

    @NotBlank(message = "Please enter city!")
    private String city;

    @NotBlank(message = "Please enter state!")
    private String state;

    @NotBlank(message = "Please enter country!")
    private String country;

    @Column(name = "postal_code")
    @NotBlank(message = "Please enter postal code!")
    private String postalCode;

    @Column(name = "is_billing")
    private boolean isBilling;

    @Column(name = "is_shipping")
    private boolean isShipping;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isBilling() {
        return isBilling;
    }

    public void setBilling(boolean billing) {
        isBilling = billing;
    }

    public boolean isShipping() {
        return isShipping;
    }

    public void setShipping(boolean shipping) {
        isShipping = shipping;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", isBilling=" + isBilling +
                ", isShipping=" + isShipping +
                '}';
    }
}
