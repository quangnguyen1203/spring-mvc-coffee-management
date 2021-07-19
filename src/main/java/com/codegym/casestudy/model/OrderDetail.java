package com.codegym.casestudy.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetail_id;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    private String product_name;
    private Long amount;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(Order order, String product_name, Long amount) {
        this.order = order;
        this.product_name = product_name;
        this.amount = amount;
    }

    public OrderDetail(Long orderDetail_id, Order order, String product_name, Long amount) {
        this.orderDetail_id = orderDetail_id;
        this.order = order;
        this.product_name = product_name;
        this.amount = amount;
    }

    public OrderDetail(Long orderDetail_id, Order order, String product_name, Long amount, double price) {
        this.orderDetail_id = orderDetail_id;
        this.order = order;
        this.product_name = product_name;
        this.amount = amount;
        this.price = price;
    }

    public Long getOrderDetail_id() {
        return orderDetail_id;
    }

    public void setOrderDetail_id(Long orderDetail_id) {
        this.orderDetail_id = orderDetail_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
}
