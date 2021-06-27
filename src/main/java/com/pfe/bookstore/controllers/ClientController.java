package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.CartDTO;
import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.repositories.IClientRepository;
import com.pfe.bookstore.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/{id}/addCart")
    public ResponseEntity<?> addToCart(@PathVariable("id") Long id,@RequestParam("bookId") Long bookId){
    Book book = clientService.addToCart(id,bookId);
    if(book == null)
         return ResponseEntity.badRequest().body("Not Added");

        return ResponseEntity.ok().body("Added successful");
    }
   @GetMapping("/{id}/cart")
    public  ResponseEntity<?> getCart(@PathVariable("id") Long id){
        CartDTO cartDTO = clientService.getCart(id);
       if(cartDTO == null)
           return ResponseEntity.badRequest().body("No Cart Found");
       return ResponseEntity.ok(cartDTO);
   }

   @DeleteMapping("/{id}/emptyCart")
    public ResponseEntity<?> emptyCart(@PathVariable("id") Long id){
       Boolean isEmpty = clientService.emptyCart(id);
       if(!isEmpty)
           return ResponseEntity.badRequest().body("Cart Not Empty");
       return ResponseEntity.ok("Cart Empty");
   }
    @DeleteMapping("/{id}/cart/{bookId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("id") Long id,@PathVariable("bookId") Long bookId){
        Boolean deleted = clientService.deleteCartItem(id, bookId);
        if(!deleted)
            return ResponseEntity.badRequest().body("Item Not Deleted");
        return ResponseEntity.ok("Item Deleted");
    }

    @PostMapping("/{id}/wishList")
    public ResponseEntity<?> addToWishList(@PathVariable("id") Long id, @RequestParam("bookId") Long bookId){
        Book book = clientService.addToWishList(id,bookId);
        if(book == null)
            return ResponseEntity.badRequest().body("Not Added");

        return ResponseEntity.ok().body("Added successful");
    }
    @GetMapping("/{id}/wishList")
    public  ResponseEntity<?> getWishList(@PathVariable("id") Long id){
        List<BookDTO> bookDTOS = clientService.getWishList(id);
        if(bookDTOS == null)
            return ResponseEntity.badRequest().body("No Cart Found");
        return ResponseEntity.ok(bookDTOS);
    }
    @DeleteMapping("/{id}/emptyWishList")
    public ResponseEntity<?> emptyWishList(@PathVariable("id") Long id){
        Boolean isEmpty = clientService.emptyWishlist(id);
        if(!isEmpty)
            return ResponseEntity.badRequest().body("WishList Not Empty");
        return ResponseEntity.ok("WishList Empty");
    }
    @DeleteMapping("/{id}/wishList/{bookId}")
    public ResponseEntity<?> deleteWishListItem(@PathVariable("id") Long id,@PathVariable("bookId") Long bookId){
        Boolean deleted = clientService.deleteWishListItem(id, bookId);
        if(!deleted)
            return ResponseEntity.badRequest().body("Item Not Deleted");
        return ResponseEntity.ok("Item Deleted");
    }
    @GetMapping("/{id}/follows")
    public ResponseEntity<?>  getFollows(@PathVariable("id") Long id){
        List<AuteurDTO> auteurDTOS = clientService.getFollows(id);
        if(auteurDTOS == null)
            return ResponseEntity.badRequest().body("Some Problems");
        return ResponseEntity.ok(auteurDTOS);
    }
    @PostMapping("/{id}/follows")
    public ResponseEntity<?>  followAuteur(@PathVariable("id") Long id,@RequestParam("auteurId") Long auteurId) {
    Boolean follwed = clientService.follow(id,auteurId);
        if(!follwed)
            return ResponseEntity.badRequest().body("Not Followed");
        return ResponseEntity.ok("Followed");
    }
}
