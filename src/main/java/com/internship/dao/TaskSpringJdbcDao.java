package com.internship.dao;

import com.internship.Mappers.TaskMapper;
import com.internship.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("spring-JDBC")
public class TaskSpringJdbcDao implements IDao<Task> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskSpringJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tasks(Id SERIAL PRIMARY KEY, name VARCHAR NOT NULL, deadLine DATE NOT NULL, userId INTEGER NOT NULL, FOREIGN KEY (userId) REFERENCES users (Id) ON DELETE CASCADE);");
    }

    @Override
    public Task add(Task task) {
        int rez = jdbcTemplate.update("INSERT INTO  tasks (name, deadLine, userId) VALUES  (?,?,?)", task.getName(), task.getDeadline(), task.getUserId());
        if (rez == 0) {
            return null;
        } else {
            return get(task.getName());
        }
    }

    @Override
    public Task get(String name) {
        Task task;
        try {
            task = jdbcTemplate.queryForObject("SELECT id, name, deadline, userId FROM tasks WHERE name = ?", new Object[]{name}, new TaskMapper());
        } catch (DataAccessException dataAccessException) {
            return null;
        }
        return task;
    }

    @Override
    public Collection<Task> getAll() {
        Collection<Task> tasks = jdbcTemplate.query("Select * from tasks", new TaskMapper());
        return tasks;
    }

    @Override
    public boolean delete(String name) {
        int rez = jdbcTemplate.update("DELETE FROM tasks WHERE name = ?", name);
        if (rez == 0) {
            return false;
        } else {
            return true;
        }
    }
}
