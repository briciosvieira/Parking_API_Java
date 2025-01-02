package com.sousa.demo_parking_api.web.mapper;

import com.sousa.demo_parking_api.entity.ClientHasVacancy;
import com.sousa.demo_parking_api.web.Dto.parkingLotDto.ParkingLotCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingLotResponseDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class ClientHasVacancyModelMapper {

    public static ClientHasVacancy toParkingLot(ParkingLotCreateDto dto){
        return new ModelMapper().map(dto, ClientHasVacancy.class);
    }

    public static ParkingLotResponseDto toDto(ClientHasVacancy lot){
        return new ModelMapper().map(lot, ParkingLotResponseDto.class);
    }
}
