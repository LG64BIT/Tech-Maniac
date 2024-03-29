package com.example.techmaniac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDetailsDto {

    private Long id;
    private String username;
    private String biography;
    private String createdAt;
}
