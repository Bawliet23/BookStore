package com.pfe.bookstore.DTO;

public class JwtResponseClient {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private ClientDTO client;
    public JwtResponseClient(String jwttoken, ClientDTO client) {
        this.jwttoken = "Bearer "+jwttoken;
        this.client=client;
    }
    public String getToken() {
        return this.jwttoken;
    }

    public UserDTO getClient() {
        return client;
    }
}
