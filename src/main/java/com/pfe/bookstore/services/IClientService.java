package com.pfe.bookstore.services;


import com.pfe.bookstore.DTO.ClientDTO;
import com.pfe.bookstore.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IClientService {
    Client addClient(Client client);
    Client findUser(Long id);
    Page<ClientDTO> findUsers(Pageable page);
    void deleteClient(Long id);
    Client updateUser(Client client);
}
