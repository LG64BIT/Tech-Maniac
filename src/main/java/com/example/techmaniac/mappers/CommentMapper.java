package com.example.techmaniac.mappers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.techmaniac.dao.UserDao;
import com.example.techmaniac.dto.CommentDto;
import com.example.techmaniac.models.Comment;
import com.example.techmaniac.models.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserDao userDao;

    public List<CommentDto> mapToDto(List<Comment> comments) {
        return comments.stream().map(comment -> {
            User user = userDao.getUserById(comment.getUserId());
            String username = user.getUsername();
            if (!user.isActive()) {
                username = "Obrisani korisnik";
            }
            return CommentDto.builder().id(comment.getId()).username(username)
                    .content(comment.getContent())
                    .timestamp(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss")
                            .format(comment.getTimestamp()))
                    .build();
        }).collect(Collectors.toList());
    }
}
