package com.sousa.demo_parking_api.web.Dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sousa.demo_parking_api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacancyRespondeDto {

    private Long id;
    private String parkingCode;
    private Status status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateCreate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateUpdate;

}
