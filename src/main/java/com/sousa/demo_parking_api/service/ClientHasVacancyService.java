package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.ClientHasVacancy;
import com.sousa.demo_parking_api.repository.ClientHasVacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientHasVacancyService {

     private final ClientHasVacancyRepository repository;

     public ClientHasVacancy create (ClientHasVacancy hasVacancy){
         return repository.save(hasVacancy);
     }
}
