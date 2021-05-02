package com.pfe.bookstore;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.GenreDTO;
import com.pfe.bookstore.entities.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

@SpringBootApplication
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Book, BookDTO> bookMap = new PropertyMap<Book, BookDTO>() {
        protected void configure() {

            map().setGenres(source.getGenres().stream().map(book->modelMapper.map(book, GenreDTO.class)).collect(Collectors.toSet()));
            map().setAuteur(modelMapper.map(source.getAuteur(), AuteurDTO.class));

        }
    };

        modelMapper.addMappings(bookMap);
        return modelMapper;
    }


}
