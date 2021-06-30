package com.pfe.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long value;
    @ManyToOne(fetch = FetchType.LAZY,cascade ={
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnore
    private User user;
    @ManyToOne(fetch = FetchType.LAZY,cascade ={
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnore
    private Book book;

}
