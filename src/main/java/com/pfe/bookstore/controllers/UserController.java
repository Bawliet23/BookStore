package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.CommentDTO;
import com.pfe.bookstore.entities.Notification;
import com.pfe.bookstore.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}/notifs")
    public ResponseEntity<?> getNotifications(@PathVariable("id") Long id ){
    List notifications = userService.getNotification(id);
    if (notifications.isEmpty()){
        return ResponseEntity.ok()
                .body("Notifications Empty");
    }
    return  ResponseEntity.ok()
                .body(notifications);
    }
    @DeleteMapping("/{userId}/comment")
    public ResponseEntity<?> deleteComment(@PathVariable("userId") Long userId,@RequestParam("commentId") Long commentId ){
        Boolean aBoolean = userService.deleteComment(userId, commentId);
        if (aBoolean){
            return ResponseEntity.ok()
                    .body("Comment Deleted");
        }
        return ResponseEntity.ok()
                .body("Comment Not Deleted");
    }
    @PutMapping("/{userId}/comment")
    public ResponseEntity<?> updateComment(@PathVariable("userId") Long userId,@RequestParam("commentId") Long commentId,@RequestParam("comment") String comment ){
        CommentDTO commentDTO = userService.updateComment(userId, commentId,comment);
        if (commentDTO !=null){
            return ResponseEntity.ok()
                    .body(commentDTO);
        }
        return ResponseEntity.ok()
                .body("Comment Not Updated");
    }
}
