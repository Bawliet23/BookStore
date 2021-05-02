package com.pfe.bookstore.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    private String name;
    private Long price;
    private String description;
    private int selles;
    private String image;
    private String contenu;
    private double rating;
    @ManyToOne(fetch = FetchType.LAZY)
    private Auteur auteur;
    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    private Set<Order> orders = new HashSet<>();
    @ManyToMany(mappedBy = "mybooks")
    @JsonBackReference
    private Set<Client> owners = new HashSet<>();
    @ManyToMany(mappedBy = "wishList")
    @JsonBackReference
    private Set<Client> whishLists = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonBackReference
    private Set<Genre> genres = new HashSet<>();


    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBook(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setBook(null);
    }
    public void addOrder(Order order) {
        orders.add(order);
        order.addBook(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.removeBook(null);
    }
}
