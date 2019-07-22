package com.internship.dao;

import com.internship.model.Task;
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
public class TaskSpringJdbcDao implements IDao<Task>{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource(){
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("server");
        ds.setDatabaseName("test_db");
        ds.setUrl("jdbc:postgresql://127.0.0.1:5432/test_db");
        ds.setUser("kontsevich");
        ds.setPassword("333498316");
        return ds;
    }

    public TaskSpringJdbcDao() {
        this.dataSource = dataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tasks(Id SERIAL PRIMARY KEY, name VARCHAR NOT NULL, deadLine DATE NOT NULL, userId INTEGER NOT NULL, FOREIGN KEY (userId) REFERENCES users (Id) ON DELETE CASCADE);");

    }

    @Override
    public Task add(Task task) {
        int rez = jdbcTemplate.update("INSERT INTO  tasks (name, deadLine, userId) VALUES  (?,?,?)",task.getName(),task.getDeadline(),task.getUserId());
        if (rez == 0) {
            return null;
        } else {
            return get(task.getName());
        }
    }

    @Override
    public Task get(String name) {
        Task task;
        try{
        task = jdbcTemplate.queryForObject("SELECT id, name, deadline, userId FROM tasks WHERE name = ?", new Object[]{name}, new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet resultSet, int i) throws SQLException {
                Task task = new Task(resultSet.getString("name"));
                task.setId(resultSet.getInt("id"));
                task.setUserId(resultSet.getInt("userid"));
                task.setDeadline(resultSet.getDate("deadline").toLocalDate());
                return task;
            }
        });
        } catch (DataAccessException dataAccessException) {
            return null;
        }
        return task;
    }

    @Override
    public Collection<Task> getAll() {
        Collection<Task> tasks = jdbcTemplate.query("Select * from tasks", new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet resultSet, int i) throws SQLException {
                Task task = new Task(resultSet.getString("name"));
                task.setId(resultSet.getInt("id"));
                task.setUserId(resultSet.getInt("userid"));
                task.setDeadline(resultSet.getDate("deadline").toLocalDate());
                return task;
            }
        });
        return tasks;
    }

    @Override
    public boolean delete(String name) {
        int rez = jdbcTemplate.update("DELETE FROM tasks WHERE name = ?",name);
        if(rez == 0) {
            return false;
        }else{
            return true;
        }
    }
}
