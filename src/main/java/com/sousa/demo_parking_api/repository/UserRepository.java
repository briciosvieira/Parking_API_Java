package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


    @Query("select u.role from Users u where u.username like :username")
    Role findRoleByUsername(String username);
}
