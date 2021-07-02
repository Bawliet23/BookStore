package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface IBookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAll(Pageable page);
    Page<Book> findByOrderBySellesDesc(Pageable page);
    Page<Book> findByOrderByPriceDesc(Pageable page);
    Page<Book> findBooksByGenresIn(List<Genre> genres, Pageable pageable);
    Page<Book> findBooksByNameContaining( Pageable pageable,String name);
    @Query("SELECT b.auteur, COUNT(b.selles) FROM Book AS b GROUP BY b.auteur ORDER BY b.selles DESC")
    List<Auteur> countTopAuteur();
}
