package com.sousa.demo_parking_api.web.Dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PageableResponseDto {

private List content = new ArrayList<>();
private boolean fist;
private boolean last;
@JsonProperty("page")
private int number;
private int size;
@JsonProperty("pageElements")
private int numberOfElements;
private int totalPages;
private int totalElements;
}
