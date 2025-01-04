package com.sousa.demo_parking_api.web.Dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotResponseDto {

    private String plate;
    private String color;
    private String mark;
    private String model;
    private String clientCpf;
    private String receipt;
    private String parkingCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal discount;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime inputDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime exitDate;

}
