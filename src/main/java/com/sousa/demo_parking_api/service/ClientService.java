package com.sousa.demo_parking_api.service;


import com.sousa.demo_parking_api.customException.CpfUniqueViolationException;
import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client create (Client client) throws CpfUniqueViolationException {
        try{
            return repository.save(client);

        } catch (DataIntegrityViolationException e) {
            throw new CpfUniqueViolationException(String.format("O CPF informado já existe"));
        }
    }


}
