package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAll(Pageable page);
    Page<Book> findBooksByGenresIn(List<Genre> genres, Pageable pageable);
    Page<Book> findBooksByNameContaining( Pageable pageable,String name);

}
