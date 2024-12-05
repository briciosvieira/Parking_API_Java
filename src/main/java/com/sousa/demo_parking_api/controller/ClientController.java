package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.customException.CpfUniqueViolationException;
import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.jwt.JwtUserDetails;
import com.sousa.demo_parking_api.repository.projection.ClientProjection;
import com.sousa.demo_parking_api.service.ClientService;
import com.sousa.demo_parking_api.service.UserService;
import com.sousa.demo_parking_api.web.Dto.clienteDto.ClientCreateDto;
import com.sousa.demo_parking_api.web.Dto.clienteDto.ClienteUpdateDto;
import com.sousa.demo_parking_api.web.Dto.pageableDto.PageableDto;
import com.sousa.demo_parking_api.web.Dto.responseDto.ClientResponseDto;
import com.sousa.demo_parking_api.web.mapper.ClientModelMapper;
import com.sousa.demo_parking_api.web.mapper.PegeableMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clientes")
public class ClientController {

    private final ClientService clientservice;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> create (@RequestBody @Valid ClientCreateDto dto,
                                                     @AuthenticationPrincipal JwtUserDetails details) throws CpfUniqueViolationException {
        Client client = ClientModelMapper.toClient(dto);
        client.setUser(userService.findById(details.id()));
        clientservice.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientModelMapper.toDto(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Long id){
        Client client = clientservice.findById(id);
        return ResponseEntity.ok().body(ClientModelMapper.toDto(client));
    }

    @GetMapping
    public ResponseEntity<PageableDto> findAll(Pageable pageable){
        Page<ClientProjection> clientPage =  clientservice.findAllPageable(pageable);
        return ResponseEntity.ok(PegeableMapper.toDto(clientPage));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDto> update (@RequestBody @Valid ClienteUpdateDto dto, @PathVariable Long id){
        clientservice.update(id, dto.getName(), dto.getCpf());
        return ResponseEntity.ok().build();


    }
}
