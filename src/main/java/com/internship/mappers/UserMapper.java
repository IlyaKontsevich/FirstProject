package com.internship.mappers;

import com.internship.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class UserMapper implements RowMapper<User> {
    public Collection<User> convert(List<Object[]> rows){
        Collection<User> users = new ArrayList();
        for(Object[] row : rows){
            User user = new User();
            user.setId(Integer.parseInt(row[0].toString()));
            user.setAge(Integer.parseInt(row[1].toString()));
            user.setEmail(row[2].toString());
            user.setName(row[3].toString());
            users.add(user);
        }
        return users;
    }

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getString("name"));
        user.setId(resultSet.getInt("id"));
        user.setAge(resultSet.getInt("age"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}
