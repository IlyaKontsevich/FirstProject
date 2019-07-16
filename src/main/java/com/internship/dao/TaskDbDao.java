package com.internship.dao;

import com.internship.model.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TaskDbDao implements IDao<Task>{

    public boolean add(Task task){
        try {
            int rez = getStatement().executeUpdate("INSERT INTO  tasks (name, deadLine, userId) VALUES  ('" + task.getName() + "','" + task.getDeadline() + "', '" + task.getUserId() + "')");
            closeConnection(getConnection());
            if (rez == 0) {
                return false;
            } else {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean delete(String name){
        try {
            int rez = getStatement().executeUpdate("DELETE FROM tasks WHERE name = '" + name + "'");
            closeConnection(getConnection());
            if (rez == 0) {
                return false;
            } else {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Task get(String name) {
        try {
            ResultSet resultSet = getStatement().executeQuery("SELECT id, deadline, userId FROM tasks WHERE name = '" + name + "'");
            if (!resultSet.next()) {
                closeConnection(getConnection());
                return null;
            } else {
                Date taskDeadLine = resultSet.getDate("deadline");//read task dead line
                Task task = new Task(name, taskDeadLine);//create new task
                task.setId(resultSet.getInt("id"));
                task.setUserId(resultSet.getInt("userid"));
                closeConnection(getConnection());
                return task;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Task> getAll(){
        Map<String, Task> tasks = new HashMap();
        try {
            ResultSet resultSet = getStatement().executeQuery("select * from tasks");
            while (resultSet.next()) {
                String taskName = resultSet.getString(2);//read task name
                Date taskDeadLine = resultSet.getDate(3);//read task dead line
                Task task = new Task(taskName, taskDeadLine);//create new task
                task.setId(resultSet.getInt(1));
                task.setUserId(resultSet.getInt(4));
                tasks.put(task.getName(), task);//add task in Map
            }
            return tasks.values();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tasks.values();
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test_db", "kontsevich", "333498316");
        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }

    public Statement getStatement(){
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tasks(Id SERIAL PRIMARY KEY, name VARCHAR NOT NULL, deadeLine DATE NOT NULL, userId INTEGER NOT NULL, FOREIGN KEY (userId) REFERENCES users (Id) ON DELETE CASCADE);");

            return statement;
        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }

    public void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
