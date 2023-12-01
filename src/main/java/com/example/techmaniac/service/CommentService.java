package com.example.techmaniac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.techmaniac.dao.CommentDao;
import com.example.techmaniac.models.Comment;
import com.example.techmaniac.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;

    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }

    public List<Comment> getCommentsByReviewId(Long reviewId) {
        return commentDao.getCommentsByReviewId(reviewId);
    }

    public Long addComment(Comment comment) {
        comment.setUserId(SecurityUtils.getUserFromContext().getId());
        return commentDao.save(comment);
    }
}
