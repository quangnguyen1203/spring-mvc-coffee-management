package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("select od from OrderDetail od where od.order.order_id = :id")
    Iterable<OrderDetail> findByOrder_id(@Param("id") Long id);

}
