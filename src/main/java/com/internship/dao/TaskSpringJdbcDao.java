package com.internship.dao;

import com.internship.mappers.TaskMapper;
import com.internship.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class TaskSpringJdbcDao implements ITaskDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskSpringJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tasks(Id SERIAL PRIMARY KEY, name VARCHAR NOT NULL, deadLine DATE NOT NULL, userId INTEGER NOT NULL, FOREIGN KEY (userId) REFERENCES users (Id) ON DELETE CASCADE);");
    }

    @Override
    public Task add(Task task) {
        if(0 != jdbcTemplate.queryForObject("Select COUNT(*) from tasks WHERE name = ? AND userid = ?",new Object[] {task.getName(),task.getUserId()}, Integer.class)) {
            return null;
        }
        jdbcTemplate.update("INSERT INTO  tasks (name, deadLine, userId) VALUES  (?,?,?)", task.getName(), task.getDeadline(), task.getUserId());
        return jdbcTemplate.queryForObject("SELECT * FROM tasks WHERE name = ? AND userid = ?", new Object[]{task.getName(),task.getUserId()}, new TaskMapper());
    }

    @Override
    public Task get(Integer id) {
        Task task;
        try {
            task = jdbcTemplate.queryForObject("SELECT id, name, deadline, userId FROM tasks WHERE id = ?", new Object[]{id}, new TaskMapper());
        } catch (DataAccessException dataAccessException) {
            return null;
        }
        return task;
    }

    public Collection<Task> getPage(Integer position, Integer userId){
        Collection<Task> tasks = jdbcTemplate.query("Select * from tasks where userid = '"+userId+"' LIMIT 3 OFFSET '"+position+"'", new TaskMapper());
        return tasks;
    }
    public Integer getSize(Integer userId) {
        Integer count = jdbcTemplate.queryForObject("Select COUNT (*) from tasks where userid = '"+userId+"'", Integer.class);
        return count;
    }

    @Override
    public Integer update(Task task) {
        String sql = "update tasks set name = ?,deadline = ? where id = ? ";
        return jdbcTemplate.update(sql,task.getName(),task.getDeadline(),task.getId());
    }

    @Override
    public Collection<Task> getAll() {
        Collection<Task> tasks = jdbcTemplate.query("Select * from tasks", new TaskMapper());
        return tasks;
    }

    @Override
    public boolean delete(Integer id) {
        int rez = jdbcTemplate.update("DELETE FROM tasks WHERE id = ?", id);
        if (rez == 0) {
            return false;
        } else {
            return true;
        }
    }
}
