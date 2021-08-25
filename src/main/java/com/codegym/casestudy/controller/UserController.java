package com.codegym.casestudy.controller;

import com.codegym.casestudy.model.Role;
import com.codegym.casestudy.model.User;
import com.codegym.casestudy.serivce.role.IRoleService;
import com.codegym.casestudy.serivce.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @ModelAttribute("roles")
    public Iterable<Role> roles(){
        return roleService.findAll();
    }

    @GetMapping("/list-user")
    public ModelAndView getAllUser(){
        ModelAndView modelAndView = new ModelAndView("/dashboard/user/list");
        modelAndView.addObject("users",userService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/dashboard/user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        Optional<Role> role = roleService.findById(user.getRole().getId());
        user.setRole(role.get());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/edit-user/{id}")
    public ResponseEntity<User> userResponseEntity(@PathVariable Long id){
        User userOptional = userService.findById(id).get();
        return new ResponseEntity<>(userOptional,HttpStatus.OK);
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user,@PathVariable Long id){
        user.setUser_id(id); ;
        user.getRole().setName(roleService.findById(user.getRole().getId()).get().getName());
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }

}