package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ICartRepository extends JpaRepository<Cart,Long> {
}
