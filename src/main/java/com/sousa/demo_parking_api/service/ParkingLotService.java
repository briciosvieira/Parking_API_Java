package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.repository.ClientParkingLotRepository;
import com.sousa.demo_parking_api.repository.ClientRepository;
import com.sousa.demo_parking_api.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParkingLotService {

    private final ClientParkingLotRepository clientParkingLotRepository;
    private final ClientRepository clientRepository;
    private final VacancyRepository vacancyRepository;



}
