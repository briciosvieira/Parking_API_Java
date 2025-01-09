package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.repository.ParkingSpotRepository;
import com.sousa.demo_parking_api.repository.projection.ParkingSpotProjectionDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParkingSpotService {

     private final ParkingSpotRepository repository;

     @Transactional
     public ParkingSpot save(ParkingSpot parkingSpot){
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

    @Transactional
    public long getTotalNumberOfTimesParking(String cpf) {
         return repository.countByClientCpfAndExitDateIsNotNull(cpf);
    }

    @Transactional
    public Page<ParkingSpotProjectionDto> findByAllClientCpf(String clientCpf, Pageable pageable) {
         return repository.findByClientCpf(clientCpf, pageable);
    }

    public Page<ParkingSpotProjectionDto> findByAllClientUserId(Long id, Pageable pageable) {
         return repository.findByClientUserId(id, pageable);
    }
}
