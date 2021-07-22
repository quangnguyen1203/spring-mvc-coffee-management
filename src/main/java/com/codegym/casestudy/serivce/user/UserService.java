package com.codegym.casestudy.serivce.user;

import com.codegym.casestudy.model.User;
import com.codegym.casestudy.model.UserPrinciple;
import com.codegym.casestudy.repository.IUserRepository;
import com.codegym.casestudy.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserPrincipal findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();

            authorities.add(user.getRole().getCode());

//            if (null != user.getRoles()) user.getRoles().forEach(r -> {
//                authorities.add(r.getRoleKey());
//                r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
//            });

            userPrincipal.setUserId(user.getUser_id());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }

//    @Override
//    public Iterable<User> findAllByOrderByRole_id() {
//        return userRepository.findAllByOrderByRole_id();
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

//        UserDetails userDetails = (UserDetails) userOptional.get();

        return UserPrinciple.build(userOptional.get());
//        return (UserDetails) userOptional.get();
    }


}