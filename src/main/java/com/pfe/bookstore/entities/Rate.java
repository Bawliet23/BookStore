package com.pfe.bookstore.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberRates;
}
