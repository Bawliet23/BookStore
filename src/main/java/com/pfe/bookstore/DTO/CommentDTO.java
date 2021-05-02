package com.pfe.bookstore.DTO;


import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String contenu;
    private UserDTO user;
}
