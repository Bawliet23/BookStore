package com.pfe.bookstore.DTO;


import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Long id;
    private String contenu;
    private Date createdAt;
    private UserDTO user;
    private Long bookId;
}
