package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.repository.projection.ClientProjectionDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c from Client c")
    Page<ClientProjectionDto> findAllPageable(Pageable pageable);


}
