package com.codegym.casestudy.serivce.product;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.Optional;


@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAllByOrderByProduct_idDesc() {
        return productRepository.findAllByOrderByProduct_idDesc();
    }

    @Override
    public Iterable<Product> findAllProduct_idDesc() {
        return productRepository.findAllProduct_idDesc();
    }

    @Override
    public void restoreProductById(Long id) {
        productRepository.restoreProductById(id);
    }



    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public Iterable<Product> findAllByCategoryCategory_id(Long id){
        return productRepository.findAllByCategoryCategory_id(id);
    }


}
