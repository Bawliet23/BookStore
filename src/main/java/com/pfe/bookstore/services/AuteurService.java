package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.repositories.IAuteurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuteurService implements IAuteurService {
    @Autowired
    private IAuteurRepository auteurRepositor;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelmapper;
    @Override
    public Auteur addAuteur(Auteur auteur) {
        auteur.setPassword(passwordEncoder.encode(auteur.getPassword()));
        return auteurRepositor.save(auteur);
    }

    @Override
    public AuteurDTO getAuteur(Long id) {
        Optional<Auteur> auteur = auteurRepositor.findById(id);
        if (auteur.isPresent()) {
            return modelmapper.map(auteur.get(),AuteurDTO.class);
        }
        return null;
    }
}
