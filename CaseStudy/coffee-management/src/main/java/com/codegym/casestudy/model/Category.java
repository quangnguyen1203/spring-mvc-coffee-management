package com.codegym.casestudy.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    private String category_name;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    private List<Product> products;

    public Category() {
    }

    public Category(Long category_id) {
        this.category_id = category_id;
    }

    public Category(Long category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public Category(Long category_id, String category_name, List<Product> products) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.products = products;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
