package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.CommentDTO;
import com.pfe.bookstore.DTO.NotificationDTO;
import com.pfe.bookstore.entities.Comment;
import com.pfe.bookstore.entities.Notification;
import com.pfe.bookstore.entities.User;
import com.pfe.bookstore.repositories.ICommentRepository;
import com.pfe.bookstore.repositories.INotificationRepository;
import com.pfe.bookstore.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ICommentRepository commentRepository;
    private final INotificationRepository notificationRepo;


    public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper, ICommentRepository commentRepository, INotificationRepository notificationRepo) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.notificationRepo = notificationRepo;
    }


    @Override
    public List<NotificationDTO> getNotification(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
          return new ArrayList<>();
        }
         User user = byId.get();
        return  user.getNotifications().stream().map(notification -> modelMapper.map(notification, NotificationDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteComment(Long commentId) {
        Optional<Comment> byId = commentRepository.findById(commentId);
        if(byId.isPresent()){
            Comment comment = byId.get();
            commentRepository.delete(comment);
                return true;
            }
        return false;
        }

    @Override
    public CommentDTO updateComment(Long userId, Long commentId, String commentS) {
        Optional<Comment> byId = commentRepository.findById(commentId);
        if(byId.isPresent()){
            Comment comment = byId.get();
            if (comment.getUser().getId().equals(userId)){
                comment.setContenu(commentS);
               Comment c =  commentRepository.save(comment);
                return modelMapper.map(c,CommentDTO.class);
            }
        }


        return null;
    }

    @Override
    public void NotificationSeen(Long id) {
        Optional<Notification> byId = notificationRepo.findById(id);
        if(byId.isPresent()){
            Notification notification =byId.get();
            notification.setSeen(true);
            notificationRepo.save(notification);
        }
    }


}
