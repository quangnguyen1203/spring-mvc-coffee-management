package com.codegym.casestudy.controller;


import com.codegym.casestudy.model.OrderDetail;
import com.codegym.casestudy.serivce.order_detail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView listOrderDetail() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/order/listOrderDetail");
        modelAndView.addObject("orderDetails",orderDetailService.findAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<OrderDetail>> orderResponseEntity(@PathVariable Long id){
        Iterable<OrderDetail> orderDetail = orderDetailService.findByOrder_id(id);
        return new ResponseEntity<>(orderDetail,HttpStatus.OK);
    }

}
