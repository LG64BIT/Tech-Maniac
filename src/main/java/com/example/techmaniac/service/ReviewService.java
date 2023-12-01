package com.example.techmaniac.service;

import java.util.List;

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

    public List<Review> getAllReviews() {
        return reviewDao.getAllReviews();
    }

    public List<Review> getReviewsByAuthorId(Long authorId) {
        return reviewDao.getReviewsByAuthorId(authorId);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Long addReview(Review review) {
        review.setAuthorId(SecurityUtils.getUserFromContext().getId());
        return reviewDao.save(review);
    }
}
