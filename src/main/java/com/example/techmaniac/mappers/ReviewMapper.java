package com.example.techmaniac.mappers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.techmaniac.dao.CommentDao;
import com.example.techmaniac.dto.ReviewDetailsDto;
import com.example.techmaniac.models.Comment;
import com.example.techmaniac.models.Review;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final CommentDao commentDao;
    private final CommentMapper commentMapper;

    public ReviewDetailsDto map(Review review) {
        List<Comment> comments = commentDao
                .getCommentsByReviewId(review.getId());
        return ReviewDetailsDto.builder().id(review.getId())
                .title(review.getTitle()).description(review.getDescription())
                .likeCount(review.getLikeCount()).authorId(review.getAuthorId())
                .comments(commentMapper.mapToDto(comments))
                .createdAt(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss")
                        .format(review.getCreatedAt()))
                .build();
    }
}
