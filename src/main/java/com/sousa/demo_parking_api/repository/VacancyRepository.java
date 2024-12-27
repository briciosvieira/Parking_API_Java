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

    @Query("SELECT v FROM Vacancy v")
    Optional<Vacancy> findByCode(String parkingCode);

    @Query("SELECT v FROM Vacancy v")
    Page<VacancyProtectionDto> findAllPageable(Pageable pageable);

    Optional<Vacancy> findFirstByStatus(Status statusSpace);
}
