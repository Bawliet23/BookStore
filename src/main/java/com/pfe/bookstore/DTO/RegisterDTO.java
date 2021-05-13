package com.pfe.bookstore.DTO;

import lombok.Data;

@Data
public class RegisterDTO extends UserDTO{
    private String password;
}
