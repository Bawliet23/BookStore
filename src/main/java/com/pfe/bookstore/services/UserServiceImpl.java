package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.NotificationDTO;
import com.pfe.bookstore.entities.Notification;
import com.pfe.bookstore.entities.User;
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
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;




    @Override
    public List<NotificationDTO> getNotification(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
          return new ArrayList<>();
        }
         User user = byId.get();
        return  user.getNotifications().stream().map(notification -> modelMapper.map(notification, NotificationDTO.class)).collect(Collectors.toList());
    }
}
