package com.example.techmaniac.mappers.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.InstanceNotFoundException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.techmaniac.enums.Role;
import com.example.techmaniac.models.User;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            return User.builder().id(rs.getLong("id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .password(rs.getString("password"))
                    .username(rs.getString("username"))
                    .biography(rs.getString("bio"))
                    .active(rs.getBoolean("activity"))
                    .createdAt(rs.getTimestamp("created_at"))
                    .role(Role.getByCode(rs.getInt("role"))).build();
        } catch (InstanceNotFoundException | SQLException e) {
            return null;
        }
    }
}
