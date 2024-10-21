package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
