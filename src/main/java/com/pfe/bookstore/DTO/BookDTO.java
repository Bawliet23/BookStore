package com.pfe.bookstore.DTO;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class BookDTO {

    private Long id;
    private String name;
    private Long price;
    private String description;
    private int selles;
    private String image;
    private String contenu;
    private double rating;
    private String  auteurUsername;
    private Set<GenreDTO> genres = new HashSet<>();
    private Set<CommentDTO> comments = new HashSet<>();
}
