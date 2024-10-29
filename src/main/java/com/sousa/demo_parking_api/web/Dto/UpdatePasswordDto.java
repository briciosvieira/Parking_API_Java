package com.sousa.demo_parking_api.web.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@NoArgsConstructor
@ToString
public class UpdatePasswordDto {

    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 5, max = 20, message = "A senha deve conter no mínimo 5 caracteres e no maximo 20")
    private String newPassword;
    @NotBlank
    private String confirmNewPassword;
}
