package com.internship.store;

import com.internship.model.*;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

public class TaskStore {

    /*public void addInfo(Map<String, Task> tasks) throws ClassNotFoundException, SQLException {
        String name = "kontsevich";
        String password = "333498316";
        String url = "jdbc:postgresql://127.0.0.1:5432/test_db";
        Class.forName ("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(url,name,password);
             Statement statement = connection.createStatement()){
              statement.executeUpdate("drop table tasks");
              statement.executeUpdate("CREATE TABLE tasks(id int NOT NULL, name varchar NOT NULL, data varchar, userid int NOT NULL);");
            for (Task task : tasks.values()){
                int id = task.getId();
                String taskName = task.getName();
                String data = String.valueOf(task.getDeadline());
                int userId = task.getUserId();
                statement.executeUpdate("INSERT INTO tasks (id, name, data, userid) VALUES ('"+ id +"', '"+ taskName +"', '"+ data +"', '"+userId + "');");
            }

        }
    }

    public int getMaxId() throws ParseException, SQLException, ClassNotFoundException {
        Map<String, Task> tasks = new HashMap();
        tasks = getInfo();
        int maxId = -1;
        for (Task task : tasks.values()){
            if (task.getId() > maxId){
                maxId = task.getId();
            }
        }
        maxId++;
        return maxId;
    }


    public Map<String, Task> getInfo() throws ParseException, ClassNotFoundException, SQLException {
        String name = "kontsevich";
        String password = "333498316";
        String url = "jdbc:postgresql://127.0.0.1:5432/test_db";
        Class.forName ("org.postgresql.Driver");
        Map<String, Task> tasks = new HashMap();

        try (Connection connection = DriverManager.getConnection(url,name,password);
             Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tasks(id int NOT NULL, name varchar NOT NULL, data varchar, userid int NOT NULL);");
            ResultSet resultSet =  statement.executeQuery("select * from tasks");
            while (resultSet.next()){
                String taskName = resultSet.getString(2);//read task name
                String taskDeadLine = resultSet.getString(3);//read task dead line
                Task task = new Task(taskName,taskDeadLine);//create new task
                task.setId(resultSet.getInt(1));
                task.setUserId(resultSet.getInt(4));
                tasks.put(task.getName(),task);//add task in Map
            }
        }
        return tasks;
    }
*/
}