package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.enums.Role;
import com.sousa.demo_parking_api.repository.UserRepository;
import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.customException.PasswordInvalidException;
import com.sousa.demo_parking_api.customException.UsernameUniqueViolationException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserService {

    private  UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }


    //save
    @Transactional
    public User save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return repository.save(user);

        } catch (DataIntegrityViolationException ex){
           throw new  UsernameUniqueViolationException(String.format("Username %s ja cadastrado",user.getUsername()));
        }
    }

    //getById
    @Transactional()
    public User findById(Long id){
            return repository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado"));
    }

    //getAll
    @Transactional
    public List<User> getAll() {
        return repository.findAll();
    }

    //Patch
    @Transactional
    public User patchPassword(Long id, String password, String newPassword, String confirmNewPassword) {


        if (!newPassword.equals(confirmNewPassword)){
            throw new PasswordInvalidException("Senhas não conferem");
        }

        User user = findById(id);
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordInvalidException("Senha esta incorreta");
        }

            user.setPassword(passwordEncoder.encode(newPassword));
            user.setDateUpdate(LocalDateTime.now());
            return repository.save(user);
    }

    //Delete
    @Transactional
    public void  delete(Long id) {
        User user = findById(id);
       repository.delete(user);
    }

    @Transactional
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(()->new
                EntityNotFoundException(String.format("Usuário com o login: %s não encontrado", username)));
    }

    @Transactional
    public Role findRoleByUsername(String username) {
        return repository.findRoleByUsername(username);
    }
}
