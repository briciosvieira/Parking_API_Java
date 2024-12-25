package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.ClientParkingLot;
import com.sousa.demo_parking_api.repository.ClientParkingLotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientParkingLotService {

     private final ClientParkingLotRepository repository;

     public ClientParkingLot create (ClientParkingLot parkingLot){
         return repository.save(parkingLot);
     }
}
