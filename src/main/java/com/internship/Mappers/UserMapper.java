package com.internship.Mappers;

import com.internship.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getString("name"));
        user.setId(resultSet.getInt("id"));
        return user;
    }
}
