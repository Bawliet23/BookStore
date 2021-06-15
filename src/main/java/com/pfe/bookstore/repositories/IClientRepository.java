package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IClientRepository extends  IUserBaseRepository<Client>{
    List<Client> findClientsByFallows(List<Auteur> auteurs);
}
