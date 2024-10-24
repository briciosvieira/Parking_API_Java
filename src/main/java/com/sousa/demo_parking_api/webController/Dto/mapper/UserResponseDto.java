package com.sousa.demo_parking_api.webController.Dto.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserResponseDto {

    private Long id;
    private String uername;
    private String role;


}
