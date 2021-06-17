package com.pfe.bookstore.DTO;

import com.pfe.bookstore.utils.Env;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class BookDTO1 {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private int selles;
    private String image;
    private String contenu;
    private double rating;
    private Set<GenreDTO> genres = new HashSet<>();
    private Set<CommentDTO> comments = new HashSet<>();

    public String getContenu() {
        return Env.getUrlImages()+contenu;
    }

    public String getImage() {
        return Env.getUrlImages()+image;
    }
}
