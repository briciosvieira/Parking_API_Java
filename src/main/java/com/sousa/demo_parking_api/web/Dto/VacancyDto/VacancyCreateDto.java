package com.sousa.demo_parking_api.web.Dto.VacancyDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacancyCreateDto {

    @Size(min = 4, max = 4)
    private String parkingCode;

    @NotBlank
    @Pattern(regexp = "LIVRE|OCUPADO")
    private String status;
}
