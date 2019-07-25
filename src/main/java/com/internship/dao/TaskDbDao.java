package com.internship.dao;

import com.internship.model.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Profile("dbSystem")
public class TaskDbDao implements ITaskDao{

    public TaskDbDao() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tasks(Id SERIAL PRIMARY KEY, name VARCHAR NOT NULL, deadLine DATE NOT NULL, userId INTEGER NOT NULL, FOREIGN KEY (userId) REFERENCES users (Id) ON DELETE CASCADE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Task> getPage(Integer position) {
        Collection<Task> tasks = new ArrayList<Task>();
        try {
            ResultSet resultSet = getStatement("Select * from tasks LIMIT 3 OFFSET '"+position+"'").executeQuery();
            while (resultSet.next()) {//while exists user
                tasks.add(setTaskInfo(resultSet));//add user in Map
            }
            closeConnection(getConnection());
            return tasks;//return collection of User
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Integer getSize() {
        try {
            ResultSet rs = getStatement("select count(*) from tasks").executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Task add(Task task) {
        try {
            PreparedStatement preparedStatement = getStatement("INSERT INTO  tasks (name, deadLine, userId) VALUES  (?,?,?)");
            preparedStatement.setString(1, task.getName());//add info in Db
            preparedStatement.setDate(2, Date.valueOf(task.getDeadline()));//add info in Db
            preparedStatement.setInt(3, task.getUserId());//add info in DB
            int rez = preparedStatement.executeUpdate();
            closeConnection(getConnection());
            if (rez == 0) {
                return null;
            } else {
                return get(task.getName(),task.getUserId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean delete(Integer id) {
        try {
            PreparedStatement preparedStatement = getStatement("DELETE FROM tasks WHERE id = ?");
            preparedStatement.setInt(1, id);//delete tasks by name
            int rez = preparedStatement.executeUpdate();
            closeConnection(getConnection());
            if (rez == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Task get(String name, Integer userId) {
        try {
            PreparedStatement preparedStatement = getStatement("SELECT * FROM tasks WHERE name = ? AND userId = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                closeConnection(getConnection());
                return null;
            } else {
                Task task = setTaskInfo(resultSet);
                closeConnection(getConnection());
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Task get(Integer id) {
        try {
            PreparedStatement preparedStatement = getStatement("SELECT * FROM tasks WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                closeConnection(getConnection());
                return null;
            } else {
                Task task = setTaskInfo(resultSet);
                closeConnection(getConnection());
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Task> getAll() {
        Collection<Task> tasks = new ArrayList<Task>();
        try {
            ResultSet resultSet = getStatement("Select * from tasks").executeQuery();
            while (resultSet.next()) {
                Task task = setTaskInfo(resultSet);
                tasks.add(task);
            }
            closeConnection(getConnection());
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test_db", "kontsevich", "333498316");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Task setTaskInfo(ResultSet resultSet) {
        try {
            String taskName = resultSet.getString("name");//read task name
            Task task = new Task(taskName);
            task.setDeadline(resultSet.getDate("deadline").toLocalDate());//read task dead line
            task.setId(resultSet.getInt("id"));
            task.setUserId(resultSet.getInt("userid"));
            return task;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PreparedStatement getStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
