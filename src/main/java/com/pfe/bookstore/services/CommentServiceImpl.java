package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.CommentDTO;
import com.pfe.bookstore.entities.*;
import com.pfe.bookstore.repositories.IBookRepository;
import com.pfe.bookstore.repositories.IClientRepository;
import com.pfe.bookstore.repositories.ICommentRepository;
import com.pfe.bookstore.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO,Comment.class);
        User user =userRepository.getOne(comment.getUser().getId());
        comment.setUser(user);
        Book book = bookRepository.getOne(comment.getBook().getId());
        comment.setBook(book);
        commentRepository.save(comment);


    }
}
