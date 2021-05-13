package com.pfe.bookstore.services;

import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.repositories.IAuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuteurService implements IAuteurService {
    @Autowired
    private IAuteurRepository auteurRepositor;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Auteur addAuteur(Auteur auteur) {
        auteur.setPassword(passwordEncoder.encode(auteur.getPassword()));
        return auteurRepositor.save(auteur);
    }
}
