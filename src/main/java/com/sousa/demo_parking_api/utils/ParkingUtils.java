package com.sousa.demo_parking_api.utils;

import java.time.LocalDateTime;

public class ParkingUtils {

    // exemple
    // 2024-12-25T19:15:48.616463500
    // 20241225-191548

    public static String generetReceipt(){
        LocalDateTime date = LocalDateTime.now();
        String receipt = date.toString().substring(0,19);
        return receipt.replace("-","")
                .replace(":","").replace("T","-");
    }

}
