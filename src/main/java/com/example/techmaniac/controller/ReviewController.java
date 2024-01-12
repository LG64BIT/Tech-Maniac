package com.example.techmaniac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techmaniac.dto.ReviewDetailsDto;
import com.example.techmaniac.models.Review;
import com.example.techmaniac.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDetailsDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDetailsDto> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    @GetMapping("/author/{authorId}")
    public List<Review> getReviewsByAuthorId(@PathVariable Long authorId) {
        return reviewService.getReviewsByAuthorId(authorId);
    }

    @PostMapping
    public ResponseEntity<Long> addReview(@RequestBody @Valid Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @PutMapping("/like/{reviewId}")
    public ResponseEntity<HttpStatus> addLike(@PathVariable Long reviewId) {
        reviewService.addLike(reviewId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/dislike/{reviewId}")
    public ResponseEntity<HttpStatus> removeLike(@PathVariable Long reviewId) {
        reviewService.removeLike(reviewId);
        return ResponseEntity.ok().build();
    }
}
