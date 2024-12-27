package com.sousa.demo_parking_api.repository;

import com.sousa.demo_parking_api.entity.ClientHasVacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientHasVacancyRepository extends JpaRepository<ClientHasVacancy, Long> {
}
