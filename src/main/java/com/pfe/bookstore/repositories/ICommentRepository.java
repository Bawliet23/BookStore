package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
