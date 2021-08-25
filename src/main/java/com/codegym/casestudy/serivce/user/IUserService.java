package com.codegym.casestudy.serivce.user;
import com.codegym.casestudy.model.User;
import com.codegym.casestudy.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends UserDetailsService {

//    Iterable<User> findAllByOrderByRole_id();
    Iterable<User> findAll();
    User createUser(User user);

    User save(User user);

    Optional<User> findById(Long id);

    void remove(Long id);

    UserPrincipal findByUsername(String username);

    User findByName(String username);
}