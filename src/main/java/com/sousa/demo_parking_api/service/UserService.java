package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.repository.UserRepository;
import com.sousa.demo_parking_api.runtimeException.EntityNotFoundException;
import com.sousa.demo_parking_api.runtimeException.PasswordInvalidException;
import com.sousa.demo_parking_api.runtimeException.UsernameUniqueViolationException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    //save
    @Transactional
    public User save(User user) {
        try {
            return repository.save(user);

        } catch (DataIntegrityViolationException ex){
           throw new  UsernameUniqueViolationException(String.format("Username %s ja cadastrado",user.getUsername()));
        }
    }

    //getById
    @Transactional()
    public User getById(Long id){
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

        User user = getById(id);
        if (!password.equals(user.getPassword())){
            throw new PasswordInvalidException("Sua senha esta incorreta");
        }

            user.setPassword(newPassword);
            return repository.save(user);
    }

    //Delete
    public void  delete(Long id) {
        User user = getById(id);
       repository.delete(user);
    }
}
