package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


public interface IAuteurRepository extends  IUserBaseRepository<Auteur>{
    Page<Auteur> findAll(Pageable page);
}
