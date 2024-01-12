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
        String sql = "SELECT * FROM users WHERE activity=1";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id=?";
        Object[] params = { id };
        return jdbcTemplate.queryForObject(sql, userRowMapper, params);
    }

    public List<User> getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=? AND activity=1";
        Object[] params = { username };
        return jdbcTemplate.query(sql, userRowMapper, params);
    }

    public boolean exists(String username) {
        String sql = "SELECT * FROM users WHERE username=? AND activity=1";
        Object[] params = { username };
        return jdbcTemplate.query(sql, userRowMapper, params).size() != 0;
    }

    public Long save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, password, bio, role, activity, created_at) VALUES (?, ?, ?, ?, ?, ?, 1, NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                        new int[] { 1 });
                int i = 1;
                preparedStatement.setString(i++, user.getFirstName());
                preparedStatement.setString(i++, user.getLastName());
                preparedStatement.setString(i++, user.getUsername());
                preparedStatement.setString(i++, user.getPassword());
                preparedStatement.setString(i++, user.getBiography());
                preparedStatement.setInt(i++, user.getRole().getCode());
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void delete(Long id) {
        String sql = "UPDATE users SET activity=0 WHERE id=?";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                        new int[] { 1 });
                int i = 1;
                preparedStatement.setLong(i++, id);
                return preparedStatement;
            }
        });
    }
}
