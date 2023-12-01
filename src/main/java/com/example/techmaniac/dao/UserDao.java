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

import com.example.techmaniac.mappers.rowMappers.UserRowMapper;
import com.example.techmaniac.models.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id=?";
        Object[] params = { id };
        return jdbcTemplate.queryForObject(sql, userRowMapper, params);
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        Object[] params = { username };
        return jdbcTemplate.queryForObject(sql, userRowMapper, params);
    }

    public boolean exists(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        Object[] params = { username };
        return jdbcTemplate.query(sql, userRowMapper, params).size() != 0;
    }

    public Long save(User user) {
        String sql = "INSERT INTO users (username, password, bio, role, created_at) VALUES (?, ?, ?, ?, NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                        new int[] { 1 });
                int i = 1;
                preparedStatement.setString(i++, user.getUsername());
                preparedStatement.setString(i++, user.getPassword());
                preparedStatement.setString(i++, user.getBiography());
                preparedStatement.setInt(i++, user.getRole().getCode());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
