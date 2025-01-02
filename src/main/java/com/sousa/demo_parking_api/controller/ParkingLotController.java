package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.entity.ClientHasVacancy;
import com.sousa.demo_parking_api.service.ParkingLotService;
import com.sousa.demo_parking_api.web.Dto.parkingLotDto.ParkingLotCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingLotResponseDto;
import com.sousa.demo_parking_api.web.mapper.ClientHasVacancyModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/parking")
public class ParkingLotController {

    private final ParkingLotService lotService;

    @PostMapping("/check-in")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingLotResponseDto> chekin(@RequestBody @Valid ParkingLotCreateDto dto){
        ClientHasVacancy clientHasVacancy = ClientHasVacancyModelMapper.toParkingLot(dto);
        lotService.checkIn(clientHasVacancy);
        ParkingLotResponseDto responseDto = ClientHasVacancyModelMapper.toDto(clientHasVacancy);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{receipt}")
                .buildAndExpand(clientHasVacancy.getReceipt()).toUri();
        return ResponseEntity.created(location).body(responseDto);
    }
}
