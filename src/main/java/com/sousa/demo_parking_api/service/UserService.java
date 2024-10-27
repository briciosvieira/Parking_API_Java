package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Transactional
    public User getById(Long id){
        return repository.findById(id).orElseThrow(()->  new  RuntimeException("Usuário não encontado"));
    }

    @Transactional
    public List<User> getAll() {
        return repository.findAll();
    }

    @Transactional
    public User patchPassword(Long id, String password, String newPassword, String confirmNewPassword) {

        User user = getById(id);
        if (!newPassword.equals(confirmNewPassword)){
            throw new RuntimeException("Senhas não conferem");
        }

        if (!password.equals(user.getPassword())){
            throw new RuntimeException("Sua senha esta incorreta");
        }
        user.setPassword(newPassword);
        return repository.save(user);
    }

    public void  delete(Long id) {
        User user = getById(id);
       repository.delete(user);
    }
}
