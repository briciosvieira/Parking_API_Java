package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParkingSpotService {

     private final ParkingSpotRepository repository;

     @Transactional
     public ParkingSpot create (ParkingSpot parkingSpot){
         try {
             return repository.save(parkingSpot);

         } catch (RuntimeException e) {
             throw new EntityNotFoundException(String.format("Não é possível salvar, a placa do carro já existe no banco de dados"));
         }
     }

    @Transactional
    public ParkingSpot findByReceipt(String receipt) {
         return repository.findByReceipt(receipt).orElseThrow(()-> new EntityNotFoundException(String.format("Recibo não encontrado")));
    }
}
