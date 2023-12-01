package com.example.techmaniac.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ReviewDetailsDto {

    private Long id;
    private String title;
    private String description;
    private Long likeCount;
    private Long authorId;
    private Timestamp createdAt;
}
