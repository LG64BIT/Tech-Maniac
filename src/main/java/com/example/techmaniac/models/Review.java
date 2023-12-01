package com.example.techmaniac.models;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Review {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Long likeCount; 
    @NotNull
    private Long authorId;
    private Timestamp createdAt;
}
