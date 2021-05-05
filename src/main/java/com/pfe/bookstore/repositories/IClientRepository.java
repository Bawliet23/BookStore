package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IClientRepository extends  IUserBaseRepository<Client>{
}