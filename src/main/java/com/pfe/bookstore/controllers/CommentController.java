package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.CommentDTO;
import com.pfe.bookstore.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/addComment")
    public  ResponseEntity<Void> addComment(@RequestBody CommentDTO commentDTO){
        commentService.addComment(commentDTO);
        return ResponseEntity.ok().build();
    }

}
