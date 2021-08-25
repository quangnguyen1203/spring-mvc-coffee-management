package com.codegym.casestudy.controller;


import com.codegym.casestudy.serivce.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dashboard")
public class HomeController {

    @Autowired
    private IOrderService orderService;

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/home");
        modelAndView.addObject("userInfo", getPrincipal());
        modelAndView.addObject("orders",orderService.findAll());
        return modelAndView;
    }
}
