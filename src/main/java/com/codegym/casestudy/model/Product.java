package com.codegym.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String product_name;
    private Long amount;
    private double price;
    private String image;
    private String description;

    @JsonIgnore
    @Column(columnDefinition = "boolean default false")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String product_name, Long amount, double price, String image, String description, Category category) {
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public Product(String product_name, double price, String image, String description, Category category) {
        this.product_name = product_name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public Product(Long product_id, String product_name, Long amount, double price, String image, String description, Category category) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public Product(String product_name, Long amount, double price, String image, String description, boolean isDelete, Category category) {
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.description = description;
        this.isDelete = isDelete;
        this.category = category;
    }

    public Product(Long product_id, String product_name, Long amount, double price, String image, String description, boolean isDelete, Category category) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.description = description;
        this.isDelete = isDelete;
        this.category = category;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
