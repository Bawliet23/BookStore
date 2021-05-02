package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository<T extends User> extends JpaRepository<T,Long> {
}
