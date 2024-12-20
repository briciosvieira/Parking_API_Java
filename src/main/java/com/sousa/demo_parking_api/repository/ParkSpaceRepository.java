package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.ParkSpace;
import com.sousa.demo_parking_api.repository.projection.ClientProjectionDto;
import com.sousa.demo_parking_api.repository.projection.ParkSpaceProkectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParkSpaceRepository extends JpaRepository<ParkSpace, Long> {


    Optional<ParkSpace> findByCode(String code);

    @Query("SELECT p FROM ParkSpace p")
    Page<ParkSpaceProkectionDto> findAllPageable( Pageable pageable);
}
