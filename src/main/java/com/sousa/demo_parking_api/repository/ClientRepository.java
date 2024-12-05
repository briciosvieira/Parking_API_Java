package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.repository.projection.ClientProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c.id as id, c.name as name, c.cpf as cpf from Client c")
    Page<ClientProjection> findAllPageable(Pageable pageable);


}
