package com.example.techmaniac.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.techmaniac.models.User;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtils {

    public User getUserFromContext() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
