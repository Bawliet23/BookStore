package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.AuteurDTO1;
import com.pfe.bookstore.DTO.BookDTO1;
import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.repositories.IAuteurRepository;
import com.pfe.bookstore.repositories.IBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuteurService implements IAuteurService {
    @Autowired
    private IAuteurRepository auteurRepositor;
    @Autowired
    private IBookRepository bookRepository;
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
    public AuteurDTO1 getAuteur(Long id) {
        Optional<Auteur> auteur = auteurRepositor.findById(id);
        if (auteur.isPresent()) {
            AuteurDTO1 au = modelmapper.map(auteur.get(), AuteurDTO1.class);
            au.setBooks(auteur.get().getBooks().stream().map(book -> modelmapper.map(book,BookDTO1.class)).collect(Collectors.toList()));
            return  au;
        }
         return null;

    }

    @Override
    public Page<AuteurDTO> getAuteurs(Pageable page) {
        return auteurRepositor.findAll(page).map(auteur -> modelmapper.map(auteur,AuteurDTO.class));
    }

    @Override
    public List<AuteurDTO1> getTopAuteurs() {
        return bookRepository.countTopAuteur().stream().map(auteur -> modelmapper.map(auteur,AuteurDTO1.class)).collect(Collectors.toList());
    }
//    @Override
//    public List<AuteurDTO> getFollows(Long id) {
//        Auteur client = auteurRepositor.getOne(id);
//        return client.getFallows().stream().map(auteur -> modelMapper.map(auteur,AuteurDTO.class)).collect(Collectors.toList());
//    }
}
