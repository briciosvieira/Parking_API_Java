package com.sousa.demo_parking_api.web.mapper;

import com.sousa.demo_parking_api.web.Dto.responseDto.PageableDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE )
public class PegeableMapper {

    public static PageableDto toDto(Page page){
        return new ModelMapper().map(page, PageableDto.class);
    }
}
