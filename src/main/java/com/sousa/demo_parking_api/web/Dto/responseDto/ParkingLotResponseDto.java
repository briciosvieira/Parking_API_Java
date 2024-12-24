package com.sousa.demo_parking_api.web.Dto.responseDto;

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
public class ParkingLotResponseDto {

    private String plate;
    private String color;
    private String mark;
    private String model;
    private String clientCpf;
    private BigDecimal value;
    private BigDecimal discount;
    private String receipt; //recibo
    private String code;
    private LocalDateTime inputDate;
    private LocalDateTime exitDate;

}
