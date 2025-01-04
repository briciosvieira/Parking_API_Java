package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {


    Optional<ParkingSpot> findByReceipt(String receipt);
}
