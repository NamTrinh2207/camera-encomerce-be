package com.example.cameraincome.controller;

import com.example.cameraincome.model.product.Comment;
import com.example.cameraincome.service.product.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Comment>> showAllComment() {
        Iterable<Comment> comments = commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Optional<Comment> optionalComment = commentService.findById(id);
        if (optionalComment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.remove(id);
        return new ResponseEntity<>(optionalComment.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> editComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> optionalComment = commentService.findById(id);
        if (optionalComment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setId(optionalComment.get().getId());
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        Optional<Comment> optionalComment = commentService.findById(id);
        return optionalComment.map(comment
                -> new ResponseEntity<>(comment, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
