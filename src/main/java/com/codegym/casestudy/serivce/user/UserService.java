package com.codegym.casestudy.serivce.user;
import com.codegym.casestudy.model.User;
import com.codegym.casestudy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public Iterable<User> findAllByOrderByRole_id() {
        return userRepository.findAllByOrderByRole_id();
    }
}