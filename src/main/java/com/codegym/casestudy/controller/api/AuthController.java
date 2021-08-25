package com.codegym.casestudy.controller.api;

import com.codegym.casestudy.model.JwtResponse;
import com.codegym.casestudy.model.User;
import com.codegym.casestudy.security.JwtUtil;
import com.codegym.casestudy.security.UserPrincipal;

import com.codegym.casestudy.serivce.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody User user) {

        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
        if (!new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getUsername(),
                userDetails.getAuthorities());
        auth.setDetails(userDetails);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//
//
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);

//        String jwt = jwtUtil.generateTokenLogin(authentication);
        String jwt = jwtUtil.generateToken(userPrincipal);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserPrincipal currentUser = userService.findByUsername(userPrincipal.getUsername());

//        Token token = new Token();
//        token.setToken(jwtUtil.generateToken(userPrincipal));
//        token.setTokenExpDate(jwtUtil.generateExpirationDate());
//        token.setCreatedBy(userPrincipal.getUserId());
//        tokenService.createToken(token);
//        return ResponseEntity.ok(token.getToken());

        JwtResponse jwtResponse = new JwtResponse(
                jwt,
                currentUser.getUserId(),
                userDetails.getUsername(),
//                currentUser.getFullName(),
                user.getEmail(),
                userDetails.getAuthorities()
        );

        ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(60 * 60 * 60 * 1000)
                .domain("localhost")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                .body(jwtResponse);
    }

    @GetMapping("/users")
//    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<Iterable<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


}
