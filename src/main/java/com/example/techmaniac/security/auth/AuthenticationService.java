package com.example.techmaniac.security.auth;

import javax.management.InstanceAlreadyExistsException;
import javax.security.auth.login.AccountExpiredException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.techmaniac.dao.UserDao;
import com.example.techmaniac.enums.Role;
import com.example.techmaniac.models.User;
import com.example.techmaniac.security.config.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Throwable.class)
    public AuthenticationResponse register(RegisterRequest request)
            throws IllegalArgumentException, IllegalAccessException,
            InstanceAlreadyExistsException {
        if (userDao.exists(request.getUsername())) {
            throw new InstanceAlreadyExistsException("Username already exists");
        }
        User user = User.builder().firstName(request.getFirstName())
                .lastName(request.getLastName()).username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .biography(request.getBiography()).role(Role.USER).build();
        userDao.save(user);
        var jwtToken = jwtService.generateToken(user.getFieldsMap(), user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
            throws IllegalArgumentException, IllegalAccessException,
            AccountExpiredException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        var user = userDao.getUserByUsername(request.getUsername());
        if (user.size() == 0) {
            throw new AccountExpiredException(
                    "This account is deleted by admin");
        }
        var jwtToken = jwtService.generateToken(user.get(0).getFieldsMap(),
                user.get(0));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
