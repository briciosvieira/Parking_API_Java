package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.service.ClientParkingSpotService;
import com.sousa.demo_parking_api.web.Dto.parkingLotDto.ParkingSpotCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingSpotResponseDto;
import com.sousa.demo_parking_api.web.mapper.ParkingSpotModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/parking")
public class ParkingSpotController {

    private final ClientParkingSpotService clientParkingSpotService;

    @PostMapping("/check-in")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingSpotResponseDto> chekin(@RequestBody @Valid ParkingSpotCreateDto dto){

        ParkingSpot parkingSpot = ParkingSpotModelMapper.toParkingLot(dto);
        clientParkingSpotService.checkIn(parkingSpot);

        ParkingSpotResponseDto responseDto = ParkingSpotModelMapper.toDto(parkingSpot);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{receipt}")
                .buildAndExpand(parkingSpot.getReceipt()).toUri();

        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping("/{receipt}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingSpotResponseDto> findByReceipt(@PathVariable String receipt){
        ParkingSpot parkingSpot = clientParkingSpotService.findByReceipt(receipt);
        return ResponseEntity.ok(ParkingSpotModelMapper.toDto(parkingSpot));
    }

}
