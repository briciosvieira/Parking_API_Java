package com.sousa.demo_parking_api.web.mapper;


import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.web.Dto.clienteDto.ClientCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ClientResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


public class ClientModelMapper {

    public static Client toClient(ClientCreateDto dto){
        return new ModelMapper().map(dto, Client.class);
    }

    public static ClientResponseDto toDto(Client client){
        return new ModelMapper().map(client, ClientResponseDto.class);
    }

}
