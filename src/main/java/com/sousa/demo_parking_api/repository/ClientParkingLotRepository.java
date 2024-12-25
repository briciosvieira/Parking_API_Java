package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.ClientParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientParkingLotRepository extends JpaRepository<ClientParkingLot, Long> {
}
