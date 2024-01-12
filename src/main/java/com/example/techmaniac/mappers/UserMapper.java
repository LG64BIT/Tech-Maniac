package com.example.techmaniac.mappers;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.techmaniac.dto.UserDetailsDto;
import com.example.techmaniac.dto.UserOverviewDto;
import com.example.techmaniac.models.User;

@Component
public class UserMapper {

    public UserOverviewDto mapToOverview(User user) {
        return UserOverviewDto.builder().id(user.getId())
                .username(user.getUsername()).role(user.getRole())
                .createdAt(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss")
                        .format(user.getCreatedAt()))
                .build();
    }

    public UserDetailsDto maptoDetails(User user) {
        return UserDetailsDto.builder().id(user.getId())
                .username(user.getUsername()).biography(user.getBiography())
                .createdAt(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss")
                        .format(user.getCreatedAt()))
                .build();
    }
}
