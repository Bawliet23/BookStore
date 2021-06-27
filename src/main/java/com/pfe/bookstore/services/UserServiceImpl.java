package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.NotificationDTO;
import com.pfe.bookstore.entities.Comment;
import com.pfe.bookstore.entities.Notification;
import com.pfe.bookstore.entities.User;
import com.pfe.bookstore.repositories.ICommentRepository;
import com.pfe.bookstore.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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


    public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper, ICommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
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
    public Boolean deleteComment(Long userId, Long commentId) {
        Optional<Comment> byId = commentRepository.findById(commentId);
        if(byId.isPresent()){
            Comment comment = byId.get();
            if (comment.getUser().getId().equals(userId) || comment.getBook().getAuteur().getId().equals(userId)){
                commentRepository.delete(comment);
                return true;
            }
        }


        return false;
    }
}
