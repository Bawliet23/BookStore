package com.pfe.bookstore.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.bookstore.entities.Rate;
import com.pfe.bookstore.utils.Env;
import lombok.Data;

import java.util.*;

@Data
public class BookDTO {

    private Long id;
    private String name;
    private Long price;
    private String description;
    private int selles;
    private String image;
    private String contenu;
    private String language;
    @JsonIgnore
    private List<RateDTO> rates = new ArrayList<>();
    private RateDTO1 rating =new RateDTO1();
    private AuteurDTO  auteur;
    private Set<GenreDTO> genres = new HashSet<>();
    private Set<CommentDTO> comments = new HashSet<>();

    public String getContenu() {
        return Env.getUrlImages()+contenu;
    }

    public String getImage() {
        return Env.getUrlImages()+image;
    }

    public RateDTO1 getRating() {
        this.rating.setNbrRates(this.rates.size());
        if (this.rates.size()>0) {
            Long rat = 0L;
            for (RateDTO r : this.rates) {
                rat += r.getValue();
            }
                 this.rating.setValue(rat/this.rates.size());
        }
        return this.rating;
    }
}
