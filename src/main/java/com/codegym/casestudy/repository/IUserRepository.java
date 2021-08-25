package com.codegym.casestudy.repository;

import com.codegym.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

//    @Transactional
//    @Modifying
//    @Query("select u from User u order by u.role.id")
//    Iterable<User> findAllByOrderByRole_id();

    User findByUsername(String username);
}