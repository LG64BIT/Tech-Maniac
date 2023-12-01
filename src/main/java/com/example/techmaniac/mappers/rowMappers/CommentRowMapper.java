package com.example.techmaniac.mappers.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.techmaniac.models.Comment;

@Component
public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Comment.builder().id(rs.getLong("id"))
                .content(rs.getString("content")).userId(rs.getLong("user_id"))
                .reviewId(rs.getLong("review_id"))
                .timestamp(rs.getTimestamp("created_at")).build();
    }

}
