package com.sousa.demo_parking_api.web.mapper;

import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.web.Dto.parkingLotDto.ParkingSpotCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingSpotResponseDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<ParkingSpotResponseDto> listDto(List<ParkingSpot> parkingSpot){
        return parkingSpot.stream().map(ParkingSpotModelMapper::toDto).collect(Collectors.toList());
    }
}
