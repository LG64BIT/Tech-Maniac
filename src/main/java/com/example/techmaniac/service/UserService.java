package com.example.techmaniac.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.techmaniac.dao.UserDao;
import com.example.techmaniac.dto.UserDetailsDto;
import com.example.techmaniac.dto.UserOverviewDto;
import com.example.techmaniac.mappers.UserMapper;
import com.example.techmaniac.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    public List<UserOverviewDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserOverviewDto> userOverviews = new ArrayList<>();
        users.forEach(
                user -> userOverviews.add(userMapper.mapToOverview(user)));
        return userOverviews;
    }

    public UserDetailsDto getUserById(Long id) {
        User user = userDao.getUserById(id);
        return userMapper.maptoDetails(user);
    }
}
