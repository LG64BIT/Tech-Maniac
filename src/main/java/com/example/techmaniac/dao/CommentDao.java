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

import com.example.techmaniac.mappers.rowMappers.CommentRowMapper;
import com.example.techmaniac.models.Comment;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentDao {

    private final JdbcTemplate jdbcTemplate;
    private final CommentRowMapper commentRowMapper;

    public List<Comment> getCommentsByReviewId(Long reviewId) {
        String sql = "SELECT * FROM comments WHERE review_id=?";
        Object[] params = { reviewId };
        return jdbcTemplate.query(sql, commentRowMapper, params);
    }

    public Comment getCommentById(Long id) {
        String sql = "SELECT * FROM comments WHERE id=?";
        Object[] params = { id };
        return jdbcTemplate.queryForObject(sql, commentRowMapper, params);
    }

    public Long save(Comment comment) {
        String sql = "INSERT INTO comments (content, user_id, review_id, created_at) VALUES (?, ?, ?, NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                        new int[] { 1 });
                int i = 1;
                preparedStatement.setString(i++, comment.getContent());
                preparedStatement.setLong(i++, comment.getUserId());
                preparedStatement.setLong(i++, comment.getReviewId());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
