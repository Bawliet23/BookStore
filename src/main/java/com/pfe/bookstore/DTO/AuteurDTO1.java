package com.pfe.bookstore.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class AuteurDTO1 extends UserDTO {
    private String bio;
    List<BookDTO1> books = new ArrayList<>();
}
