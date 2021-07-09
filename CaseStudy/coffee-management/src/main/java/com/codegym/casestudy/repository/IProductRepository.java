package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.isDelete = FALSE order by p.product_id desc")
    Iterable<Product> findAllByOrderByProduct_idDesc();

    @Transactional
    @Modifying
    @Query("update Product p set p.isDelete = true where p.product_id = :id")
    void deleteProductById(@Param("id") Long id);

    @Query("select p from Product p where p.isDelete = true order by p.product_id desc")
    Iterable<Product> findAllProduct_idDesc();

    @Transactional
    @Modifying
    @Query("update Product p set p.isDelete = false where p.product_id = :id")
    void restoreProductById(@Param("id") Long id);
}
