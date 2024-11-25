package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.customException.CpfUniqueViolationException;
import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.jwt.JwtUserDetails;
import com.sousa.demo_parking_api.service.ClientService;
import com.sousa.demo_parking_api.service.UserService;
import com.sousa.demo_parking_api.web.Dto.ClientCreateDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ClientResponseDto;
import com.sousa.demo_parking_api.web.mapper.ClientModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clientes")
public class ClientController {
    @Autowired
    private ClientService clientservice;
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN') || hasAnyRole('CLIENTE')")
    public ResponseEntity<ClientResponseDto> create (@RequestBody @Valid ClientCreateDto dto,
                                                     @AuthenticationPrincipal JwtUserDetails details) throws CpfUniqueViolationException {
        Client client = ClientModelMapper.toClient(dto);
        client.setUser(userService.findById(details.id()));
        clientservice.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientModelMapper.toDto(client));
    }
}
