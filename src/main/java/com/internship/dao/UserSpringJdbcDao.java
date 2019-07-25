package com.internship.dao;

import com.internship.mappers.UserMapper;
import com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
@Profile("spring-JDBC")
public class UserSpringJdbcDao implements IUserDao{
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
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE name=?", new Object[]{user.getName()}, new UserMapper());
        }
    }

    @Override
    public User get(Integer id) {
        User user;
        try {
        user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[]{id}, new UserMapper());
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

    public Collection<User> getPage(Integer position){
        Collection<User> tasks = jdbcTemplate.query("Select * from users LIMIT 3 OFFSET '"+position+"'", new UserMapper());
        return tasks;
    }

    public Integer getSize() {
        Integer count = jdbcTemplate.queryForObject("Select COUNT(*) from users", Integer.class);
        return count;
    }

    @Override
    public boolean delete(Integer id) {
        int rez = jdbcTemplate.update("DELETE FROM users WHERE id = ?",id);
        if(rez == 0) {
            return false;
        }else{
            return true;
        }
    }
}