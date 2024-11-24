package com.sousa.demo_parking_api.web.Dto.responseDto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class ClientResponseDto {

    private Long id;
    private String name;
    private String cpf;


}
