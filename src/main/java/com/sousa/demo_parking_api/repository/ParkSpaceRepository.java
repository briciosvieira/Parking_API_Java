package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.ParkSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkSpaceRepository extends JpaRepository<ParkSpace, Long> {
}
