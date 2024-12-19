package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.entity.ParkSpace;
import com.sousa.demo_parking_api.service.ParkSpaceService;
import com.sousa.demo_parking_api.web.Dto.parkingSpaceDto.ParkSpaceCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkSpaceRespondeDto;
import com.sousa.demo_parking_api.web.mapper.ParkSpaceModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/vagas")
@Slf4j
public class ParkSpaceController {

    private final ParkSpaceService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody @Valid ParkSpaceCreateDto dto){
        ParkSpace parkSpace = service.save(ParkSpaceModelMapper.toParkSpace(dto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{code}")
                .buildAndExpand(parkSpace.getCode()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkSpaceRespondeDto> getCode(@PathVariable String code){
        ParkSpace parkSpace = service.findByCode(code);
        return ResponseEntity.ok(ParkSpaceModelMapper.toRespondeDto(parkSpace));
    }
}
