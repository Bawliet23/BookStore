package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IClientRepository extends  IUserBaseRepository<Client>{
    List<Client> findClientsByFallowsIn(List<Auteur> auteurs);
}
