package com.pfe.bookstore.DTO;

import java.io.Serializable;

public class JwtResponseAuteur implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private AuteurDTO auteur;
    public JwtResponseAuteur(String jwttoken, AuteurDTO client) {
        this.jwttoken = "Bearer "+jwttoken;
        this.auteur=client;
    }
    public String getToken() {
        return this.jwttoken;
    }

    public UserDTO getAuteur() {
        return auteur;
    }
}
