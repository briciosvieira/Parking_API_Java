package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
