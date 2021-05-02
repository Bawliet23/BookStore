package com.pfe.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Genre  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "genres",fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

}
