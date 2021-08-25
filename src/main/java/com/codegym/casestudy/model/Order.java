package com.codegym.casestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private double total_price;
    private Time order_time;
    private Date order_date;

//    @JsonIgnore
    @OneToMany(targetEntity = Product.class,fetch = FetchType.EAGER)
    private List<Product> products;

    @JsonIgnore
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(targetEntity = Voucher.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(double total_price, Time order_time, Date order_date, List<Product> products, User user, Voucher voucher) {
        this.total_price = total_price;
        this.order_time = order_time;
        this.order_date = order_date;
        this.products = products;
        this.user = user;
        this.voucher = voucher;
    }

    public Order(double total_price, Time order_time, Date order_date, Voucher voucher) {
        this.total_price = total_price;
        this.order_time = order_time;
        this.order_date = order_date;
        this.voucher = voucher;
    }

    public Order(Long order_id, double total_price, Time order_time, Date order_date, List<Product> products, User user, Voucher voucher) {
        this.order_id = order_id;
        this.total_price = total_price;
        this.order_time = order_time;
        this.order_date = order_date;
        this.products = products;
        this.user = user;
        this.voucher = voucher;
    }

    public Order(double total_price, Time order_time, Date order_date, List<Product> products, Voucher voucher) {
        this.total_price = total_price;
        this.order_time = order_time;
        this.order_date = order_date;
        this.products = products;
        this.voucher = voucher;
    }



    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Time getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Time order_time) {
        this.order_time = order_time;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
