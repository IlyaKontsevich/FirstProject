package com.internship.dao;

import com.internship.model.User;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
@Profile("spring-JDBC")
public class UserSpringJdbcDao implements IDao<User>{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource dataSource(){
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("server");
        ds.setDatabaseName("test_db");
        ds.setUrl("jdbc:postgresql://127.0.0.1:5432/test_db");
        ds.setUser("kontsevich");
        ds.setPassword("333498316");
        return ds;
    }

    public UserSpringJdbcDao() {
        this.dataSource = dataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE name=?", new Object[]{name}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getString("name"));
                user.setId(resultSet.getInt("id"));
                return user;
            }
        });
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users = jdbcTemplate.query("Select * from users", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getString("name"));
                user.setId(resultSet.getInt("id"));
                return user;
            }
        });
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
