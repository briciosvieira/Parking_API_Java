package com.sousa.demo_parking_api.service;


import com.sousa.demo_parking_api.customException.CpfUniqueViolationException;
import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public Client create (Client client) throws CpfUniqueViolationException {
        try{
            return repository.save(client);

        } catch (DataIntegrityViolationException e) {
            throw new CpfUniqueViolationException(String.format("O CPF informado já existe"));
        }
    }

    @Transactional
    public Client findById(Long id) {
        return repository.findById(id).orElseThrow(()->new EntityNotFoundException("Cliente não encontrado"));
    }

    public Page<Client> findAll(Pageable pageable) {
          return repository.findAll(pageable);

    }

    @Transactional
    public void update(Long id,  String name, String cpf) {
        Client client = findById(id);

        if (!id.equals(client.getId())){
            throw new EntityNotFoundException("Cliente não encontrado.");
        }

        client.setName(name);
        client.setCpf(cpf);
        repository.save(client);
    }
}
