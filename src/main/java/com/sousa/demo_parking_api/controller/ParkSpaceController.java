package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.entity.ParkSpace;
import com.sousa.demo_parking_api.service.ParkSpaceService;
import com.sousa.demo_parking_api.web.Dto.parkingSpaceDto.ParkSpaceCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkSpaceRespondeDto;
import com.sousa.demo_parking_api.web.mapper.ParkSpaceModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController("api/v1")
public class ParkSpaceController {

    private final ParkSpaceService service;

    public ResponseEntity<ParkSpaceRespondeDto> create(@RequestBody @Valid ParkSpaceCreateDto dto){
        ParkSpace parkSpace = service.save(ParkSpaceModelMapper.toParkSpace(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ParkSpaceModelMapper.toRespondeDto(parkSpace));
    }
}
