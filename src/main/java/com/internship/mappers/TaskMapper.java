package com.internship.mappers;

import com.internship.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task(resultSet.getString("name"));
        task.setId(resultSet.getInt("id"));
        task.setUserId(resultSet.getInt("userid"));
        task.setDeadline(resultSet.getDate("deadline").toLocalDate());
        return task;
    }
}
//../user
//add 5 polei in user
//add 5 polei in task
//