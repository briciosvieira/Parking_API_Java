package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.entity.ClientHasVacancy;
import com.sousa.demo_parking_api.entity.Vacancy;
import com.sousa.demo_parking_api.enums.Status;
import com.sousa.demo_parking_api.utils.ParkingUtils;
import com.sousa.demo_parking_api.web.Dto.responseDto.ParkingLotResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ParkingLotService {

    private final ClientHasVacancyService clientHasVacancyService;
    private final ClientService clientService;
    private final VacancyService vacancyService;


    public ClientHasVacancy checkIn(ClientHasVacancy clientHasVacancy){
        Client client = clientService.findByCPF(clientHasVacancy.getClient().getCpf());
        clientHasVacancy.setClient(client);

        Vacancy vacancy = vacancyService.findByVacancyFree();
        vacancy.setStatus(Status.OCUPADO);
        clientHasVacancy.setVacancy(vacancy);

        clientHasVacancy.setInputDate(LocalDateTime.now());
        clientHasVacancy.setReceipt(ParkingUtils.generetReceipt());

        return clientHasVacancyService.create(clientHasVacancy);
    }
}
