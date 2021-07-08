package com.codegym.casestudy.serivce.product;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.serivce.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByOrderByProduct_idDesc();

}
