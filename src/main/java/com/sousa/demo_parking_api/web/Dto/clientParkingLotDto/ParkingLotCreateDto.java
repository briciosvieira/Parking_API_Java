package com.sousa.demo_parking_api.web.Dto.clientParkingLotDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ParkingLotCreateDto {

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "A paca do veículo deve seguir um padrão 'XXX-0000'.")
    private String plate;
    @NotBlank
    private String color;
    @NotBlank
    private String mark;
    @NotBlank
    private String model;
    @NotBlank
    @CPF
    private String clientCpf;
}
