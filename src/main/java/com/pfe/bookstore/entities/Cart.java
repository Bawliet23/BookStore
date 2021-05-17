package com.pfe.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cart")
@Data
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;
    @Transient
    private double totalPrice;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Client client;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "cart_book",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();


    public double getTotalPrice() {
        this.totalPrice=0;
        for (Book book : books){
            this.totalPrice+=book.getPrice();
        }
        return totalPrice;
    }
}
