package com.codegym.casestudy.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetail_id;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;
}
