package com.pfe.bookstore.services;


import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.BookDTO;
import com.pfe.bookstore.DTO.CartDTO;
import com.pfe.bookstore.DTO.ClientDTO;
import com.pfe.bookstore.entities.Book;
import com.pfe.bookstore.entities.Cart;
import com.pfe.bookstore.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IClientService {
    Client addClient(Client client);
    Book addToCart(Long id,Long bookId);
    Book addToWishList(Long id,Long bookId);
    CartDTO getCart(Long id);
    List<BookDTO> getWishList(Long id);
    Boolean follow(Long id,Long auteurId);
    List<AuteurDTO> getFollows(Long id);
    Boolean emptyCart(Long id);
    Boolean emptyWishlist(Long id);
    Boolean deleteCartItem(Long id,Long bookId);
    Boolean deleteWishListItem(Long id,Long bookId);
    Client findUser(Long id);
    Page<ClientDTO> findClients(Pageable page);
    void deleteClient(Long id);
    Client updateUser(Client client);
    Boolean makeOrder(Long clientId);
    List<BookDTO> getClientBooks(Long id);
}
