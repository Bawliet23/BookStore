package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface IUserBaseRepository<T extends User> extends JpaRepository<T,Long> {
}
