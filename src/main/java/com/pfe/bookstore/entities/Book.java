package com.pfe.bookstore.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @ToString
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    private String name;
    private double price;
    private String description;
    private int selles;
    private String image;
    private String contenu;
    private double rating;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonManagedReference
    private Auteur auteur;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("createdAt DESC")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "books",fetch = FetchType.LAZY,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnore
    private List<Cart> orders;
    @ManyToMany(mappedBy = "mybooks",fetch = FetchType.LAZY,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnore
    private List<Client> owners ;
    @ManyToMany(mappedBy = "wishList",fetch = FetchType.LAZY,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnore
    private List<Client> whishLists;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres ;


















}
