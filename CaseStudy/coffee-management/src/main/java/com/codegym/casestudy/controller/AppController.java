package com.codegym.casestudy.controller;


import com.codegym.casestudy.serivce.app.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private IAppService appService;

//    @Autowired
//    private ICategoryService categoryService;

    @ModelAttribute("products")

    @GetMapping
    public ModelAndView getAllProductPage() {
        ModelAndView modelAndView = new ModelAndView("/app/index");
        modelAndView.addObject("products", appService.findAll());
        return modelAndView;
    }
}
