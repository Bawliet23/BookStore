package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.CommentDTO;
import com.pfe.bookstore.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/addComment")
    public  ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO){
       CommentDTO c = commentService.addComment(commentDTO);
        return ResponseEntity.ok(c);
    }


}
