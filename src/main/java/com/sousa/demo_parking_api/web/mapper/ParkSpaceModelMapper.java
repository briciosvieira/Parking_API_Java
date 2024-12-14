package com.sousa.demo_parking_api.web.mapper;

import com.sousa.demo_parking_api.entity.ParkSpace;
import com.sousa.demo_parking_api.web.Dto.parkingSpaceDto.ParkSpaceCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkSpaceRespondeDto;
import org.modelmapper.ModelMapper;

public class ParkSpaceModelMapper {

    public static ParkSpace toParkSpace(ParkSpaceCreateDto dto){
        return new ModelMapper().map(dto, ParkSpace.class);
    }

    public static ParkSpaceRespondeDto toRespondeDto ( ParkSpace space){
        return new ModelMapper().map(space, ParkSpaceRespondeDto.class);
    }
}
