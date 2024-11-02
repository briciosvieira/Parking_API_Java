package com.sousa.demo_parking_api.web.mapper;


import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.web.Dto.UserCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;


public class UserModelMapper {

    public static User toUser(UserCreateDto dto){
        return new ModelMapper().map(dto, User.class);
    }

    // metodo de retorno para responder o a criação do usuario.
    public static UserResponseDto ToDto(User user){
        String role = user.getRole().name().substring("ROLE_".length());

        PropertyMap<User, UserResponseDto> props = new PropertyMap<User, UserResponseDto>() {
            @Override
            protected void configure() {

                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<User> user){
       return user.stream().map(UserModelMapper::ToDto).collect(Collectors.toList());
    }
}
