package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.ParkSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkSpaceRepository extends JpaRepository<ParkSpace, Long> {


    Optional<ParkSpace> findByCode(String code);
}
