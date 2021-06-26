package com.pfe.bookstore.repositories;

import com.pfe.bookstore.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification,Long> {
}
