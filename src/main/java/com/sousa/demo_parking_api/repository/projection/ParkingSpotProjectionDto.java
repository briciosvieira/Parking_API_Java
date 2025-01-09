package com.sousa.demo_parking_api.repository.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ParkingSpotProjectionDto {

     String getPlate();
     String getColor();
     String getMark();
     String getModel();
     String getClientCpf();
     String getReceipt();
     String getVacancyParkingCode();
     BigDecimal getValue();
     BigDecimal getDiscount();
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
     LocalDateTime getInputDate();
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
     LocalDateTime getExitDate();
}
