package com.sousa.demo_parking_api.service;


import com.sousa.demo_parking_api.customException.CodeUniqueViolationException;
import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.entity.Vacancy;
import com.sousa.demo_parking_api.repository.VacancyRepository;
import com.sousa.demo_parking_api.repository.projection.VacancyProtectionDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.sousa.demo_parking_api.enums.Status.LIVRE;

@RequiredArgsConstructor
@Service
@Slf4j
public class VacancyService {

    private final VacancyRepository repository;

    @Transactional
    public Vacancy save(Vacancy vacancy){
        try {
            return repository.save(vacancy);

        } catch (DataIntegrityViolationException e) {
            log.error("error", vacancy);
            throw new CodeUniqueViolationException("Vaga com o código já existe");
        }
    }

    @Transactional
    public Vacancy findByCode(String code){
        return repository.findByCode(code).orElseThrow(()-> new EntityNotFoundException(String.format("Vaga '%s' não encontrada!",code)));
    }

    public Page<VacancyProtectionDto> findAllPageableCode(Pageable pageable) {
        return repository.findAllPageable(pageable);
    }

    public Vacancy findByVacancyFree() {
        return repository.findFirstByStatus(LIVRE).orElseThrow(()-> new EntityNotFoundException(String.format("Nenhuma vaga livre foi encontrada")));
    }
}
