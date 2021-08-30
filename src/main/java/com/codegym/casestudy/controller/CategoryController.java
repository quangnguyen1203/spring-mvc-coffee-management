package com.codegym.casestudy.controller;

import com.codegym.casestudy.model.Category;
import com.codegym.casestudy.serivce.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView homePage() {
        return new ModelAndView("/dashboard/home");
    }

    @GetMapping("/listCategory")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView getAllCategoryPage() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/category/list");
        modelAndView.addObject("categories",categoryService.findAll());
        return modelAndView;
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> editCategory(@RequestBody Category category,@PathVariable Long id){
        category.setCategory_id(id); ;
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
    }

    @GetMapping("/edit-category/{id}")
    public ResponseEntity<Category> categoryResponseEntity(@PathVariable Long id){
        Category categoryOptional = categoryService.findById(id).get();
        return new ResponseEntity<>(categoryOptional,HttpStatus.OK);
    }
}
