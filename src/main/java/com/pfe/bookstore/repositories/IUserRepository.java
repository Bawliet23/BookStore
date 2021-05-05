package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IUserRepository extends  IUserBaseRepository<User>{
}
