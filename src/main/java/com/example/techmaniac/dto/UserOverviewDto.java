package com.example.techmaniac.dto;

import com.example.techmaniac.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserOverviewDto {

    private Long id;
    private String username;
    private Role role;
    private String createdAt;
}
