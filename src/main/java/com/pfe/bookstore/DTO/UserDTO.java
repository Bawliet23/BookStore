package com.pfe.bookstore.DTO;
import lombok.Data;
import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Date birthday;
    private String image;
    private int reportednumber;
    private String role;
}
