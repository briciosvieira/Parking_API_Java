package com.sousa.demo_parking_api.web.mapper;

import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.web.Dto.parkingLotDto.ParkingSpotCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingSpotResponseDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class ParkingSpotModelMapper {

    public static ParkingSpot toParkingLot(ParkingSpotCreateDto dto){
        return new ModelMapper().map(dto, ParkingSpot.class);
    }

    public static ParkingSpotResponseDto toDto(ParkingSpot lot){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(ParkingSpot.class, ParkingSpotResponseDto.class)
                .addMappings(mapper -> mapper.map(
                        src -> src.getVacancy().getParkingCode(),
                        ParkingSpotResponseDto::setParkingCode
                ));

        return modelMapper.map(lot, ParkingSpotResponseDto.class);
    }
}
