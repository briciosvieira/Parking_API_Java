package com.sousa.demo_parking_api.webController.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class UpdatePasswordDto {

    private String password;
    private String newPassword;
    private String confirmNewPassword;
}
