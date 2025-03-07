package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.jwt.JwtUserDetails;
import com.sousa.demo_parking_api.repository.projection.ParkingSpotProjectionDto;
import com.sousa.demo_parking_api.service.ClientParkingSpotService;
import com.sousa.demo_parking_api.web.Dto.parkingLotDto.ParkingSpotCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.PageableDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingSpotResponseDto;
import com.sousa.demo_parking_api.web.mapper.PageableModelMapper;
import com.sousa.demo_parking_api.web.mapper.ParkingSpotModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<ParkingSpotResponseDto> checkin(@RequestBody @Valid ParkingSpotCreateDto dto){

        ParkingSpot parkingSpot = ParkingSpotModelMapper.toParkingLot(dto);
        clientParkingSpotService.checkIn(parkingSpot);

        ParkingSpotResponseDto responseDto = ParkingSpotModelMapper.toDto(parkingSpot);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{receipt}")
                .buildAndExpand(parkingSpot.getReceipt()).toUri();

        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping("/check-in/{receipt}")
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    public ResponseEntity<ParkingSpotResponseDto> findByReceipt(@PathVariable String receipt){
        ParkingSpot parkingSpot = clientParkingSpotService.findByReceipt(receipt);
        ParkingSpotResponseDto dto  = ParkingSpotModelMapper.toDto(parkingSpot);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("check-out/{receipt}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingSpotResponseDto> checkOut(@PathVariable String receipt){
        ParkingSpot parkingSpot = clientParkingSpotService.chekOut(receipt);
        return ResponseEntity.ok(ParkingSpotModelMapper.toDto(parkingSpot));
    }

    @GetMapping("/{ClientCpf}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto> findByAllClientCpf(@PathVariable String ClientCpf,
                                                             @PageableDefault( size = 5, sort = "inputDate",
                                                                     direction = Sort.Direction.ASC)
                                                                        Pageable pageable){

        Page<ParkingSpotProjectionDto> projection = clientParkingSpotService.findByAllClientCpf(ClientCpf, pageable);
        PageableDto dto = PageableModelMapper.toDto(projection);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PageableDto> findByAllForUserId(@AuthenticationPrincipal JwtUserDetails user,
                                                             @PageableDefault(size = 5, sort = "vacancyParkingCode",
                                                              direction = Sort.Direction.ASC) Pageable pageable){

        Page<ParkingSpotProjectionDto> vacancyProjection = clientParkingSpotService.findByAllClientUserId(user.id(), pageable);
        PageableDto dto = PageableModelMapper.toDto(vacancyProjection);
        return ResponseEntity.ok(dto);
    }

}
