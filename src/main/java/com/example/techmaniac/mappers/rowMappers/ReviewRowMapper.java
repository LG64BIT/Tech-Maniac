package com.example.techmaniac.mappers.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.techmaniac.models.Review;

@Component
public class ReviewRowMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Review.builder().id(rs.getLong("id"))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .likeCount(rs.getLong("likes"))
                .authorId(rs.getLong("user_id"))
                .createdAt(rs.getTimestamp("created_at")).build();
    }
}
