package com.pfe.bookstore.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("auteur")
public class Auteur extends User {
    private Long rating;
    @OneToMany(
            mappedBy = "auteur",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<Book> books = new ArrayList<>();


    public void addBook(Book book) {
        books.add(book);
        book.setAuteur(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAuteur(null);
    }

}
