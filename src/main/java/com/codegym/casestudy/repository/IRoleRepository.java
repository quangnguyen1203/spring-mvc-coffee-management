package com.codegym.casestudy.repository;
import com.codegym.casestudy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
