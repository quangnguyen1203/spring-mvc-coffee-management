package com.codegym.casestudy.controller;

import com.codegym.casestudy.model.User;
import com.codegym.casestudy.serivce.order.IOrderService;
import com.codegym.casestudy.serivce.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @ModelAttribute("users")
    public Iterable<User> users(){
        return userService.findAll();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView listOrder(){
        ModelAndView modelAndView = new ModelAndView("/dashboard/order/listOrder");
        modelAndView.addObject("orders",orderService.findAll());
        return modelAndView;
    }
}
