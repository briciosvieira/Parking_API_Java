package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.entity.Vacancy;
import com.sousa.demo_parking_api.repository.projection.VacancyProtectionDto;
import com.sousa.demo_parking_api.service.VacancyService;
import com.sousa.demo_parking_api.web.Dto.VacancyDto.VacancyCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.PageableDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.VacancyResponseDto;
import com.sousa.demo_parking_api.web.mapper.VacancyModelMapper;
import com.sousa.demo_parking_api.web.mapper.PegeableMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/vagas")
@Slf4j
public class VacancyController {

    private final VacancyService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody @Valid VacancyCreateDto dto){
        Vacancy vacancy = service.save(VacancyModelMapper.toParkSpace(dto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{code}")
                .buildAndExpand(vacancy.getParkingCode()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VacancyResponseDto> getCode(@PathVariable String code){
        Vacancy vacancy = service.findByCode(code);
        return ResponseEntity.ok(VacancyModelMapper.toRespondeDto(vacancy));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto> findAll(Pageable pageable){
        Page<VacancyProtectionDto> parkSpace = service.findAllPageableCode(pageable);

        return ResponseEntity.ok(PegeableMapper.toDto(parkSpace));
    }

}
