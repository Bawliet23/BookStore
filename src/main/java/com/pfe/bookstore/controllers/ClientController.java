package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.CartDTO;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/{id}/addCart")
    public ResponseEntity<?> addToCart(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO){
    Book book = clientService.addToCart(id,bookDTO);
    if(book == null)
         return ResponseEntity.badRequest().body("Not Added");

        return ResponseEntity.ok().body("Added successful");
    }
   @GetMapping("/{id}/cart")
    public  ResponseEntity<?> getcart(@PathVariable("id") Long id){
        CartDTO cartDTO = clientService.getCart(id);
       if(cartDTO == null)
           return ResponseEntity.badRequest().body("No Cart Found");
       return ResponseEntity.ok(cartDTO);
   }
}
