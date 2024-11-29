package com.sousa.demo_parking_api.web.Dto.userDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UpdatePasswordDto {

    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 5, max = 20, message = "A senha deve conter no mínimo 5 caracteres e no maximo 20")
    private String newPassword;
    @NotBlank
    private String confirmNewPassword;
}
