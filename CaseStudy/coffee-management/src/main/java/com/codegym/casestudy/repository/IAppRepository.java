package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRepository extends JpaRepository<Product, Long> {
}
