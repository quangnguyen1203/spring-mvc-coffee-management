package com.codegym.casestudy.controller;


import com.codegym.casestudy.model.*;
import com.codegym.casestudy.repository.IOrderDetailRepository;
import com.codegym.casestudy.serivce.app.IAppService;
import com.codegym.casestudy.serivce.category.ICategoryService;
import com.codegym.casestudy.serivce.order.IOrderService;
import com.codegym.casestudy.serivce.order_detail.IOrderDetailService;
import com.codegym.casestudy.serivce.product.IProductService;
import com.codegym.casestudy.serivce.voucher.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IAppService appService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IVoucherService voucherService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IOrderService orderService;

    @ModelAttribute("order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute("vouchers")
    public Iterable<Voucher> vouchers(){
        return voucherService.findAll();
    }

    @GetMapping
    public ModelAndView getAllProductPage() {
        ModelAndView modelAndView = new ModelAndView("/app/index");
        modelAndView.addObject("products", productService.findAllByOrderByProduct_idDesc());
        modelAndView.addObject("vouchers",voucherService.findAll());
        modelAndView.addObject("categories",categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/menuProductByCategory/{id}")
    public ResponseEntity<Iterable<Product>> productResponseEntity(@PathVariable Long id){
        Iterable<Product> products = productService.findAllByCategoryCategory_id(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/allItem")
    public ResponseEntity<Iterable<Product>> productResponseEntity(){
        Iterable<Product> products = productService.findAllByOrderByProduct_idDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/addProduct/{id}")
    public ResponseEntity<Order> addProduct(@ModelAttribute("order") Order order, @PathVariable Long id){
        Product product = productService.findById(id).get();
         orderService.addProduct(order,product);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order){
//        Optional<Voucher> voucher = voucherService.findById(  order.getVoucher().getVoucher_id());
//        order.getVoucher().setVoucher_id(voucher.get().getVoucher_id());
//        Order newOrder = orderService.save(order);
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }
}
