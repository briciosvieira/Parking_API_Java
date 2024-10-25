package com.sousa.demo_parking_api.webController.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
public class UserCreateDto {

    private String username;
    private String password;
}
