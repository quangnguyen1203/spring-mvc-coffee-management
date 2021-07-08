package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p order by p.product_id desc")
    Iterable<Product> findAllByOrderByProduct_idDesc();
}
