package com.codegym.casestudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//        if (authentication.getPrincipal() == "anonymousUser") {
//            return "anonymousUser";
//        }
//        return ((UserPrincipal) authentication.getPrincipal()).getUsername();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('STAFF')")
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/login/login");
    }

    @GetMapping(value="/logout")
    public ModelAndView Logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie jwtCookieRemove = new Cookie("JWT", "");
        jwtCookieRemove.setMaxAge(0);
        response.addCookie(jwtCookieRemove);
        return new ModelAndView("/login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("/login/register");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/login/admin");
        modelAndView.addObject("userInfo", getPrincipal());
        return modelAndView;
    }

    @GetMapping("/error-403")
    public ModelAndView page403(){
        return new ModelAndView("/error-403");
    }

}
