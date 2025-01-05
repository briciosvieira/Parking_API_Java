package com.sousa.demo_parking_api.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingUtils {

    private static final Double fist_15_minutes = 5.00;
    private static final Double fist_60_minutes = 9.25;
    private static final Double aditional_15_minutes = 1.75;

    // exemple
    // 2024-12-25T19:15:48.616463500
    // 20241225-191548

    public static String generetReceipt(){
        LocalDateTime date = LocalDateTime.now();
        String receipt = date.toString().substring(0,19);
        return receipt.replace("-","")
                .replace(":","").replace("T","-");
    }

    public static BigDecimal calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        long minutes = entrada.until(saida, ChronoUnit.MINUTES);
        double total = 0.0;

        if (minutes <= 15){
            total = fist_15_minutes;

        } else if (minutes == 60){
            total = fist_60_minutes;

        } else {
            total = fist_60_minutes;

            long additionalMinutes = minutes - 60;

            long additionalBlocks = (additionalMinutes + 14) / 15;

            total += additionalBlocks * aditional_15_minutes;
        }
        return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
    }

}
