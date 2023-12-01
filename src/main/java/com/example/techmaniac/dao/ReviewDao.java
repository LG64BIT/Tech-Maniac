package com.example.techmaniac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.techmaniac.mappers.rowMappers.ReviewRowMapper;
import com.example.techmaniac.models.Review;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Repository
@RequiredArgsConstructor
public class ReviewDao {

    private final JdbcTemplate jdbcTemplate;
    private final ReviewRowMapper reviewRowMapper;

    public Review getReviewById(Long id) {
        String sql = "SELECT * FROM reviews WHERE id=?";
        Object[] params = { id };
        return jdbcTemplate.queryForObject(sql, reviewRowMapper, params);
    }

    public List<Review> getAllReviews() {
        String sql = "SELECT * FROM reviews";
        return jdbcTemplate.query(sql, reviewRowMapper);
    }

    public List<Review> getReviewsByAuthorId(Long authorId) {
        String sql = "SELECT * FROM reviews WHERE user_id=?";
        Object[] params = { authorId };
        return jdbcTemplate.query(sql, reviewRowMapper, params);
    }

    public Long save(Review review) {
        String sql = "INSERT INTO reviews (title, description, likes, user_id, created_at) VALUES (?, ?, ?, ?, NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                        new int[] { 1 });
                int i = 1;
                preparedStatement.setString(i++, review.getTitle());
                preparedStatement.setString(i++, review.getDescription());
                preparedStatement.setLong(i++, review.getAuthorId());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
