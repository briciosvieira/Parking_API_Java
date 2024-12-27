package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.repository.projection.ClientProjectionDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c from Client c")
    Page<ClientProjectionDto> findAllPageable(Pageable pageable);

    @Query("SELECT client FROM Client client JOIN client.user user WHERE user.id = :id")
    Client findDetailUserById (@Param("id") Long id);


    Optional<Client> findByCpf(String cpf);
}
