package com.sousa.demo_parking_api.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingUtils {

    private static final double fist_15_minutes = 5.00;
    private static final double fist_60_minutes = 9.25;
    private static final double aditional_15_minutes = 1.75;
    private static final double discount_percent = 0.30;

    // exemple
    // 2024-12-25T19:15:48.616463500
    // 20241225-191548

    public static String generetReceipt(){
        LocalDateTime date = LocalDateTime.now();
        String receipt = date.toString().substring(0,19);
        return receipt.replace("-","")
                .replace(":","").replace("T","-");
    }

    public static BigDecimal calculateCost(LocalDateTime entrada, LocalDateTime saida) {
        long minutes = entrada.until(saida, ChronoUnit.MINUTES);
        double total = 0.0;

        if (minutes <= 15){
            total = fist_15_minutes;

        } else if (minutes == 60){
            total = fist_60_minutes;

        } else {
            total = fist_60_minutes;

            long additionalMinutes = (long) (minutes - fist_60_minutes);

            long additionalBlocks = (additionalMinutes + 14) / 15;

            total += additionalBlocks * aditional_15_minutes;
        }
        return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
    }


    public static BigDecimal calculateDiscount (BigDecimal cost, long numberReturnClient){

        if (numberReturnClient == 10){

            BigDecimal discount = cost.multiply(BigDecimal.valueOf(discount_percent));
            return discount.setScale(2, RoundingMode.HALF_EVEN);
        }

        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);

    }

}
