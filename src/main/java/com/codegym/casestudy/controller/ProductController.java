package com.codegym.casestudy.controller;

import com.codegym.casestudy.model.Category;
import com.codegym.casestudy.model.Product;
import com.codegym.casestudy.repository.IProductRepository;
import com.codegym.casestudy.serivce.app.IAppService;
import com.codegym.casestudy.serivce.category.ICategoryService;
import com.codegym.casestudy.serivce.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/listProduct")
    public ModelAndView getAllProductPage() {
        Iterable<Product> productPage = productService.findAllByOrderByProduct_idDesc();
        ModelAndView modelAndView = new ModelAndView("/dashboard/product/list");
        modelAndView.addObject("products",productPage);
        return modelAndView;
    }

    @GetMapping("/listProductForPaging")
    public ResponseEntity<Iterable<Product>> getAll(){
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Optional<Category> category = categoryService.findById(product.getCategory().getCategory_id());
        product.getCategory().setCategory_name(category.get().getCategory_name());
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product product,@PathVariable Long id){
        product.setProduct_id(id); ;
        product.getCategory().setCategory_name(categoryService.findById(product.getCategory().getCategory_id()).get().getCategory_name());
        return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
    }

    @GetMapping("/edit-product/{id}")
    public ResponseEntity<Product> productResponseEntity(@PathVariable Long id){
        Product productOptional = productService.findById(id).get();
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> restoreProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.restoreProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/hiddenProduct")
    public ModelAndView getAllProductHiddenPage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/product/hiddenList");
        modelAndView.addObject("hiddenProducts",productService.findAllProduct_idDesc());
        return modelAndView;
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product product = productService.findById(id).get();
        product.setAmount(1L);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
