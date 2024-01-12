package com.example.techmaniac.controller;

import javax.management.InstanceAlreadyExistsException;
import javax.security.auth.login.AccountExpiredException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techmaniac.enums.Role;
import com.example.techmaniac.security.SecurityUtils;
import com.example.techmaniac.security.auth.AuthenticationRequest;
import com.example.techmaniac.security.auth.AuthenticationResponse;
import com.example.techmaniac.security.auth.AuthenticationService;
import com.example.techmaniac.security.auth.RegisterRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request)
            throws IllegalArgumentException, IllegalAccessException,
            InstanceAlreadyExistsException {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request)
            throws IllegalArgumentException, IllegalAccessException, AccountExpiredException {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/role")
    public ResponseEntity<Role> getUserRole() {
        return ResponseEntity.ok(SecurityUtils.getUserFromContext().getRole());
    }
}
