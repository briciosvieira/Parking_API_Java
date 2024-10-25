package com.sousa.demo_parking_api.webController;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.service.UserService;
import com.sousa.demo_parking_api.webController.Dto.UserCreateDto;
import com.sousa.demo_parking_api.webController.Dto.mapper.UserModelMapper;
import com.sousa.demo_parking_api.webController.Dto.UserResponseDto;
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
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto userdto){
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

    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id ,@RequestBody User user){
        User users = service.patchPassword(id, user.getPassword());
        return ResponseEntity.ok(users) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
       service.getById(id);
       service.delete(id);
       return ResponseEntity.noContent().build();
    }

}
