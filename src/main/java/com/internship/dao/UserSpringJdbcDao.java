package com.internship.dao;

import com.internship.Mappers.UserMapper;
import com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
@Profile("spring-JDBC")
public class UserSpringJdbcDao implements IDao<User>{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserSpringJdbcDao(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
          jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
    }

    @Override
    public User add(User user) {
        int rez = jdbcTemplate.update("INSERT INTO  users (name) VALUES  (?)",user.getName());
        if (rez == 0) {
            return null;
        } else {
            return get(user.getName());
        }
    }

    @Override
    public User get(String name) {
        User user;
        try {
        user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE name=?", new Object[]{name}, new UserMapper());
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users = jdbcTemplate.query("Select * from users", new UserMapper());
        return users;
    }

    @Override
    public boolean delete(String name) {
        int rez = jdbcTemplate.update("DELETE FROM users WHERE name = ?",name);
        if(rez == 0) {
            return false;
        }else{
            return true;
        }
    }
}
