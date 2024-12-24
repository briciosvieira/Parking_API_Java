package com.sousa.demo_parking_api.web.mapper;

import com.sousa.demo_parking_api.entity.ClientParkingLot;
import com.sousa.demo_parking_api.web.Dto.clientParkingLotDto.ParkingLotCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingLotResponseDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class ParkingLotModelMapper {

    public static ClientParkingLot toParkingLot(ParkingLotCreateDto dto){
        return new ModelMapper().map(dto, ClientParkingLot.class);
    }

    public static ParkingLotResponseDto toDto(ClientParkingLot lot){
        return new ModelMapper().map(lot, ParkingLotResponseDto.class);
    }
}
