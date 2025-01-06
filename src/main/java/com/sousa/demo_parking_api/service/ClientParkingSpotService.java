package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.entity.ParkingSpot;
import com.sousa.demo_parking_api.entity.Vacancy;
import com.sousa.demo_parking_api.enums.Status;
import com.sousa.demo_parking_api.utils.ParkingUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ClientParkingSpotService {

    private final ParkingSpotService parkingSpotService;
    private final ClientService clientService;
    private final VacancyService vacancyService;

    @Transactional
    public ParkingSpot checkIn(ParkingSpot parkingSpot){
        Client client = clientService.findByCPF(parkingSpot.getClient().getCpf());
        parkingSpot.setClient(client);

        Vacancy vacancy = vacancyService.findByVacancyFree();
        vacancy.setStatus(Status.OCUPADO);
        parkingSpot.setVacancy(vacancy);

        parkingSpot.setInputDate(LocalDateTime.now());
        parkingSpot.setReceipt(ParkingUtils.generetReceipt());

        return parkingSpotService.save(parkingSpot);
    }


    @Transactional
    public ParkingSpot findByReceipt(String parkingSpot) {
        return parkingSpotService.findByReceipt(parkingSpot);
    }


    @Transactional
    public ParkingSpot chekOut(String receipt) {
        ParkingSpot parkingSpot = parkingSpotService.findByReceipt(receipt);

        LocalDateTime exitDate = LocalDateTime.now();

        BigDecimal value = ParkingUtils.calculateCost(parkingSpot.getInputDate(), exitDate);
        parkingSpot.setValue(value);

        long totalNumberOfTimes = parkingSpotService.getTotalNumberOfTimesParking(parkingSpot.getClient().getCpf());

        BigDecimal discount = ParkingUtils.calculateDiscount(value, totalNumberOfTimes);
        parkingSpot.setDiscount(discount);

        parkingSpot.setExitDate(exitDate);

        parkingSpot.getVacancy().setStatus(Status.LIVRE);

        return parkingSpotService.save(parkingSpot);
    }
}
