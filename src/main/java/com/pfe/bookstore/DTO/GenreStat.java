package com.pfe.bookstore.DTO;

import com.pfe.bookstore.entities.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class GenreStat {
    private Genre genre;
    private Long selles;


}
