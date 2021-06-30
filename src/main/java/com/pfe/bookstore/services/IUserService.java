package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.CommentDTO;
import com.pfe.bookstore.DTO.NotificationDTO;

import java.util.List;

public interface IUserService {
    List<NotificationDTO> getNotification(Long id);
    Boolean deleteComment(Long commentId);
    CommentDTO updateComment(Long userId, Long commentId, String comment);

}
