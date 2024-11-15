package com.sousa.demo_parking_api.web.Dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserResponseDto {

    private String username;
    private String role;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateCreation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateUpdate;

}
