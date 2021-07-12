package com.codegym.casestudy.serivce.role;
import com.codegym.casestudy.model.Role;
import com.codegym.casestudy.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }
}