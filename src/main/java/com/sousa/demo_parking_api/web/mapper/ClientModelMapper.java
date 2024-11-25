package com.sousa.demo_parking_api.web.mapper;


import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.web.Dto.ClientCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ClientResponseDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


public class ClientModelMapper {

    public static Client toClient(ClientCreateDto dto){
        return new ModelMapper().map(dto, Client.class);
    }

    public static ClientResponseDto toDto(Client client){
        return new ModelMapper().map(client, ClientResponseDto.class);
    }
}
