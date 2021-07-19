package com.codegym.casestudy.controller;


import com.codegym.casestudy.serivce.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dashboard")
public class HomeController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/home");
        modelAndView.addObject("orders",orderService.findAll());
        return modelAndView;
    }
}
