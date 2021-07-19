package com.codegym.casestudy.controller;


import com.codegym.casestudy.model.OrderDetail;
import com.codegym.casestudy.serivce.order_detail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @PostMapping("/saveOrderDetail")
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail){
        return new ResponseEntity<>(orderDetailService.save(orderDetail), HttpStatus.CREATED);
    }

    @GetMapping
    public ModelAndView listOrderDetail() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/order/list");
        modelAndView.addObject("orderDetails",orderDetailService.findAll());
        return modelAndView;
    }
}
