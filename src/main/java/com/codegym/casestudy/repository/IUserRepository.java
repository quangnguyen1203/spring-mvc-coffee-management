package com.codegym.casestudy.repository;
import com.codegym.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("select u from User u order by u.role.role_id")
    Iterable<User> findAllByOrderByRole_id();
}