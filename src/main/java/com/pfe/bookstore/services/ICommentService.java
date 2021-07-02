package com.pfe.bookstore.services;

import com.pfe.bookstore.DTO.CommentDTO;

public interface ICommentService {
    CommentDTO addComment(CommentDTO commentDTO);
}
