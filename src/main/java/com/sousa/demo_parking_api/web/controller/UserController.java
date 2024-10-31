package com.sousa.demo_parking_api.web.controller;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.service.UserService;
import com.sousa.demo_parking_api.web.Dto.UpdatePasswordDto;
import com.sousa.demo_parking_api.web.Dto.UserCreateDto;
import com.sousa.demo_parking_api.web.mapper.UserModelMapper;
import com.sousa.demo_parking_api.web.Dto.responseDto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto userdto){
        User users = service.save(UserModelMapper.DtotoUser(userdto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserModelMapper.UserToDto(users));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserResponseDto> getById(@PathVariable Long id ){
        User user = service.getById(id);
        return ResponseEntity.ok(UserModelMapper.UserToDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<User> users = service.getAll();
        List<UserResponseDto> dtos = users.stream().map(UserModelMapper::UserToDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id ,@Valid @RequestBody UpdatePasswordDto dto){
        service.patchPassword(id, dto.getPassword(), dto.getNewPassword(), dto.getConfirmNewPassword());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

          service.getById(id);
          service.delete(id);
          return ResponseEntity.noContent().build();
    }

}