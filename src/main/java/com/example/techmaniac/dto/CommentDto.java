package com.example.techmaniac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private String content;
    private String username;
    private String timestamp;
}
