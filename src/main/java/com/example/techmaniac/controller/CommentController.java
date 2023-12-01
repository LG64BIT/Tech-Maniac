package com.example.techmaniac.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techmaniac.models.Comment;
import com.example.techmaniac.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/review/{reviewId}")
    public List<Comment> getCommentsByReviewId(@PathVariable Long reviewId) {
        return commentService.getCommentsByReviewId(reviewId);
    }

    @PostMapping
    public Long addComment(@RequestBody @Valid Comment comment) {
        return commentService.addComment(comment);
    }
}
