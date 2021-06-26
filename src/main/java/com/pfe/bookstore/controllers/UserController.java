package com.pfe.bookstore.controllers;

import com.pfe.bookstore.entities.Notification;
import com.pfe.bookstore.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}/notifications")
    public ResponseEntity<?> getNotifications(@PathVariable("id") Long id ){
    List notifications = userService.getNotification(id);
    if (notifications.isEmpty()){
        return ResponseEntity.ok()
                .body("Notifications Empty");
    }
    return  ResponseEntity.ok()
                .body(notifications);
    }
}
