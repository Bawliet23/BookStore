package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.AuteurDTO1;
import com.pfe.bookstore.entities.Auteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAuteurService {
    Auteur addAuteur(Auteur auteur);
    AuteurDTO1 getAuteur(Long id);
    Page<AuteurDTO> getAuteurs(Pageable page);
    List<AuteurDTO1> getTopAuteurs();
//    List<AuteurDTO> getFollows(Long id);
}
