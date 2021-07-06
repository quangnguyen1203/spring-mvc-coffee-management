package com.codegym.casestudy.serivce.app;

import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.repository.IAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService implements IAppService {

    @Autowired
    private IAppRepository appRepository;

    @Override
    public Iterable<Product> findAll() {
        return appRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return appRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return appRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        appRepository.deleteById(id);
    }
}
