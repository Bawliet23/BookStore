package com.pfe.bookstore.DTO;

import lombok.Data;

import java.util.List;
@Data
public class CartDTO {
    private Long id;
    private double totalPrice;
    List<BookDTO> books;
}
