package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.entity.Vacancy;
import com.sousa.demo_parking_api.enums.Status;
import com.sousa.demo_parking_api.utils.ParkingUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ClientParkingSpotService {

    private final ParkingSpotService parkingSpotService;
    private final ClientService clientService;
    private final VacancyService vacancyService;


    public ParkingSpot checkIn(ParkingSpot parkingSpot){
        Client client = clientService.findByCPF(parkingSpot.getClient().getCpf());
        parkingSpot.setClient(client);

        Vacancy vacancy = vacancyService.findByVacancyFree();
        vacancy.setStatus(Status.OCUPADO);
        parkingSpot.setVacancy(vacancy);

        parkingSpot.setInputDate(LocalDateTime.now());
        parkingSpot.setReceipt(ParkingUtils.generetReceipt());

        return parkingSpotService.create(parkingSpot);
    }


    public ParkingSpot findByReceipt(String parkingSpot) {
        return parkingSpotService.findByReceipt(parkingSpot);
    }
}
