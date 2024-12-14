package com.sousa.demo_parking_api.web.Dto.parkingSpaceDto;

import com.sousa.demo_parking_api.enums.StatusSpace;
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
public class ParkSpaceCreateDto {

    @Size(min = 4, max = 4)
    private String code;

    @NotBlank
    @Pattern(regexp = "LIVRE|OCUPADO")
    private StatusSpace space;
}
