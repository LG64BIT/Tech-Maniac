package com.example.techmaniac.mappers;

import org.springframework.stereotype.Component;

import com.example.techmaniac.dto.ReviewDetailsDto;
import com.example.techmaniac.models.Review;

@Component
public class ReviewMapper {

    public ReviewDetailsDto map(Review review) {
        return ReviewDetailsDto.builder().id(review.getId())
                .title(review.getTitle()).description(review.getDescription())
                .likeCount(review.getLikeCount()).authorId(review.getAuthorId())
                .createdAt(review.getCreatedAt()).build();
    }
}
