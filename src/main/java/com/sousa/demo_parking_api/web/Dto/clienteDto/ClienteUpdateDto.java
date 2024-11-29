package com.sousa.demo_parking_api.web.Dto.clienteDto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteUpdateDto {

    @Size(min = 3, max = 100)
    private String name;

    @Size(min = 11, max = 11, message = "O CPF precisa ter no mínimo 11 números")
    @CPF(message = "CPF inválido")
    private String cpf;
}
