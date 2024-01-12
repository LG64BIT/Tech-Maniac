package com.example.techmaniac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.techmaniac.dao.ReviewDao;
import com.example.techmaniac.dto.ReviewDetailsDto;
import com.example.techmaniac.mappers.ReviewMapper;
import com.example.techmaniac.models.Review;
import com.example.techmaniac.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewDao reviewDao;
    private final ReviewMapper reviewMapper;

    public ReviewDetailsDto getReview(Long id) {
        return reviewMapper.map(reviewDao.getReviewById(id));
    }

    public List<ReviewDetailsDto> getAllReviews() {
        List<Review> reviews = reviewDao.getAllReviews();
        return reviews.stream().map(reviewMapper::map)
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByAuthorId(Long authorId) {
        return reviewDao.getReviewsByAuthorId(authorId);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Long addReview(Review review) {
        review.setAuthorId(SecurityUtils.getUserFromContext().getId());
        review.setLikeCount(0L);
        return reviewDao.save(review);
    }

    public void addLike(Long reviewId) {
        reviewDao.addLike(reviewId);
    }

    public void removeLike(Long reviewId) {
        Review review = reviewDao.getReviewById(reviewId);
        if (review.getLikeCount() > 0) {
            reviewDao.removeLike(reviewId);
        }
    }
}
