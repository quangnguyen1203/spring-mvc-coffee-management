package com.codegym.casestudy.serivce.order;

import com.codegym.casestudy.model.Order;
import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.serivce.IGeneralService;
import org.springframework.data.repository.query.Param;

public interface IOrderService extends IGeneralService<Order> {
    void addProduct(Order order, Product product);
}
