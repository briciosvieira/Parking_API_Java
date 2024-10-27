package com.sousa.demo_parking_api.webController.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
public class UserCreateDto {

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = " O E-mail está inválido!", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String username;

    @NotBlank(message = "A senha não pode ser nula ou em branco")
    @Size(min = 5, max = 20, message = "A senha deve conter no mínimo 5 caracteres e no máximo 20")
    private String password;
}
