package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRateRepository extends JpaRepository<Rate,Long> {
}
