package com.codegym.casestudy.serivce.order;

import com.codegym.casestudy.model.Order;
import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.model.Voucher;
import com.codegym.casestudy.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    public boolean isContain(Order order, Product product){
        return order.getProducts().contains(product);
    }

    @Override
    public void addProduct(Order order, Product product){
        if (isContain(order, product)) {
            product.setAmount(product.getAmount()+1);
        } else  {
            product.setAmount(1L);
            order.getProducts().add(product);
        }
    }

    public void deleteProduct(Order order,Product product){
        order.getProducts().remove(product);
    }

    public void subtractProduct(Order order, Product product){
        if (product.getAmount() > 1){
            product.setAmount(product.getAmount()-1);
        } else {
            deleteProduct(order,product);
        }
    }

    public void addVoucher(Order order, Voucher voucher){
        order.setVoucher(voucher);
    }
}
