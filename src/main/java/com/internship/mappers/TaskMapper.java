/*package com.internship.mappers;

import com.internship.model.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TaskMapper implements RowMapper<Task> {
    public Collection<Task> convert(List<Object[]> rows){
        Collection<Task> tasks = new ArrayList();
        for(Object[] row : rows){
            Task task = new Task();
            task.setId(Integer.parseInt(row[0].toString()));
            task.setName(row[1].toString());
            task.setDeadline(LocalDate.parse(row[2].toString()));
            task.setTimeadd(LocalDate.parse(row[3].toString()));
            task.setPriority(row[4].toString());
            task.setIsdone(Boolean.parseBoolean(row[5].toString()));
            task.setUserId(Integer.parseInt(row[6].toString()));
            tasks.add(task);
        }
        return tasks;
    }
    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task(resultSet.getString("name"));
        task.setId(resultSet.getInt("id"));
        task.setUserId(resultSet.getInt("userid"));
        task.setDeadline(resultSet.getDate("deadline").toLocalDate());
        task.setTimeadd(resultSet.getDate("timeadd").toLocalDate());
        task.setIsdone(resultSet.getBoolean("isdone"));
        task.setPriority(resultSet.getString("priority"));
        return task;
    }
}*/