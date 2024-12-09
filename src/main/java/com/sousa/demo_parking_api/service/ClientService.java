package com.sousa.demo_parking_api.service;

import com.sousa.demo_parking_api.customException.CpfUniqueViolationException;
import com.sousa.demo_parking_api.customException.EntityNotFoundException;
import com.sousa.demo_parking_api.entity.Client;
import com.sousa.demo_parking_api.repository.ClientRepository;
import com.sousa.demo_parking_api.repository.projection.ClientProjectionDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public Client create(Client client) throws CpfUniqueViolationException {
        try {
            return repository.save(client);

        } catch (DataIntegrityViolationException e) {
            throw new CpfUniqueViolationException(String.format("Já existe um cadastro para o usuario ou CPF informado!"));
        }
    }

    @Transactional
    public Client findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Page<Client> findAll(Pageable pageable) {
        return repository.findAll(pageable);

    }

    @Transactional
    public void update(Long id, String name, String cpf) {
        Client client = findById(id);

        if (!id.equals(client.getId())) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
        client.setName(name);
        client.setCpf(cpf);
        repository.save(client);
    }

    @Transactional
    public Page<ClientProjectionDto> findAllPageable(Pageable pageable) {
        return repository.findAllPageable(pageable);
    }

    @Transactional
    public void delete(Long id) {
        try {
            Client client = findById(id);
            repository.delete(client);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @Transactional
    public Client findDetailUserById (Long id) {
        return repository.findDetailUserById(id);
    }
}
