package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.AuteurDTO1;
import com.pfe.bookstore.entities.Auteur;

public interface IAuteurService {
    Auteur addAuteur(Auteur auteur);
    AuteurDTO1 getAuteur(Long id);
}
