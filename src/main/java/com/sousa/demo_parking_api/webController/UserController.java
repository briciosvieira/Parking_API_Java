package com.sousa.demo_parking_api.webController;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User users = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<User> getById(@PathVariable Long id ){
        User user = service.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = service.getAll();
        return ResponseEntity.ok(users) ;
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
