package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.Vacancy;
import com.sousa.demo_parking_api.enums.Status;
import com.sousa.demo_parking_api.repository.projection.VacancyProtectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {


    Optional<Vacancy> findByCode(String code);

    @Query("SELECT p FROM ParkSpace p")
    Page<VacancyProtectionDto> findAllPageable(Pageable pageable);

    Optional<Vacancy> findFirstByStatus(Status statusSpace);
}
