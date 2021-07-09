package com.codegym.casestudy.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oder_id;
    private double total_price;
    private Time order_time;
    private Date order_date;

    @OneToMany(targetEntity = Product.class,fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(targetEntity = Voucher.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    public Order() {
    }

    public Order(double total_price, Time order_time, Date order_date, List<Product> products, User user, Voucher voucher) {
        this.total_price = total_price;
        this.order_time = order_time;
        this.order_date = order_date;
        this.products = products;
        this.user = user;
        this.voucher = voucher;
    }

    public Order(Long oder_id, double total_price, Time order_time, Date order_date, List<Product> products, User user, Voucher voucher) {
        this.oder_id = oder_id;
        this.total_price = total_price;
        this.order_time = order_time;
        this.order_date = order_date;
        this.products = products;
        this.user = user;
        this.voucher = voucher;
    }

    public Long getOder_id() {
        return oder_id;
    }

    public void setOder_id(Long oder_id) {
        this.oder_id = oder_id;
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
