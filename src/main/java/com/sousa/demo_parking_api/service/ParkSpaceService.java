package com.sousa.demo_parking_api.service;


import com.sousa.demo_parking_api.customException.CodeUniqueViolationException;
import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.entity.ParkSpace;
import com.sousa.demo_parking_api.repository.ParkSpaceRepository;
import com.sousa.demo_parking_api.repository.projection.ParkSpaceProkectionDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ParkSpaceService {

    private final ParkSpaceRepository repository;

    @Transactional
    public ParkSpace save( ParkSpace space){
        try {
            return repository.save(space);

        } catch (DataIntegrityViolationException e) {
            log.error("error", space);
            throw new CodeUniqueViolationException("Vaga com o código já existe");
        }
    }

    @Transactional
    public ParkSpace findByCode( String code){
        return repository.findByCode(code).orElseThrow(()-> new EntityNotFoundException(String.format("Vaga '%s' não encontrada!",code)));
    }

    public Page<ParkSpaceProkectionDto> findAllPageableCode(Pageable pageable) {
        return repository.findAllPageable(pageable);
    }
}
