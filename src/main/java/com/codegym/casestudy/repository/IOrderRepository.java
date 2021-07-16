package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
