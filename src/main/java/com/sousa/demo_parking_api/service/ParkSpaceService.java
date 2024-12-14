package com.sousa.demo_parking_api.service;


import com.sousa.demo_parking_api.entity.ParkSpace;
import com.sousa.demo_parking_api.repository.ParkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParkSpaceService {

    private final ParkSpaceRepository repository;

    public ParkSpace save( ParkSpace space){
        return repository.save(space);
    }
}
