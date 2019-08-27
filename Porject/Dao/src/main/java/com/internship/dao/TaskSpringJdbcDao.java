package com.internship.dao;/*package com.com.internship.dao;

import com.com.internship.mappers.TaskMapper;
import com.com.internship.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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
        jdbcTemplate.update("INSERT INTO  tasks (name, deadLine, userId,timeadd,priority,isdone) VALUES  (?,?,?,?,?,?)",
                task.getName(), task.getDeadline(), task.getUserId(),task.getTimeadd(),task.getPriority(),task.getIsdone());
        return jdbcTemplate.queryForObject("SELECT * FROM tasks WHERE name = ? AND userid = ?", new Object[]{task.getName(),task.getUserId()}, new TaskMapper());
    }

    @Override
    public Task get(Integer id) {
        Task task;
        try {
            task = jdbcTemplate.queryForObject("SELECT * FROM tasks WHERE id = ?", new Object[]{id}, new TaskMapper());
        } catch (DataAccessException dataAccessException) {
            return null;
        }
        return task;
    }
    @Override
    public Collection<Task> getPage(Integer position, Integer userId, Integer pageSize, List<String> sortType, List<String> filter){
        Collection<Task> tasks = jdbcTemplate.query("Select * from tasks WHERE userid ="+userId+filter+ sortType+
                " LIMIT '"+pageSize+"' OFFSET '"+position+"'", new TaskMapper());
        return tasks;
    }
    @Override
    public Integer getSize(Integer userId) {
        Integer count = jdbcTemplate.queryForObject("Select COUNT (*) from tasks where userid = '"+userId+"'", Integer.class);
        return count;
    }

    @Override
    public void update(Task task) {
        String sql = "update tasks set name = ?,deadline = ?,priority = ?,isdone = ? where id = ? ";
        jdbcTemplate.update(sql,task.getName(),task.getDeadline(),task.getPriority(),task.getIsdone(),task.getId());
    }

    @Override
    public Collection<Task> getAll(Integer userId) {
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
*/