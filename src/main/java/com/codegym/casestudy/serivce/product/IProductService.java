package com.codegym.casestudy.serivce.product;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.serivce.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByOrderByProduct_idDesc();

    Iterable<Product> findAllProduct_idDesc();

    void restoreProductById(Long id);

    Iterable<Product> findAllByCategoryCategory_id(Long id);
}
