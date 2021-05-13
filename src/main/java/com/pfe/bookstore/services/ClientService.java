package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.ClientDTO;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService implements IClientService{
    @Autowired
    private IClientRepository clientRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Client addClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepo.save(client);
    }

    @Override
    public Client findUser(Long id) {
        return null;
    }

    @Override
    public Page<ClientDTO> findUsers(Pageable page) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {

    }

    @Override
    public Client updateUser(Client client) {
        return null;
    }
}
