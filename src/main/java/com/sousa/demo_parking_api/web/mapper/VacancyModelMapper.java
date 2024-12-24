package com.sousa.demo_parking_api.web.mapper;

import com.sousa.demo_parking_api.entity.Vacancy;
import com.sousa.demo_parking_api.web.Dto.VacancyDto.VacancyCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.VacancyRespondeDto;
import org.modelmapper.ModelMapper;

public class VacancyModelMapper {

    public static Vacancy toParkSpace(VacancyCreateDto dto){
        return new ModelMapper().map(dto, Vacancy.class);
    }

    public static VacancyRespondeDto toRespondeDto (Vacancy space){
        return new ModelMapper().map(space, VacancyRespondeDto.class);
    }
}
