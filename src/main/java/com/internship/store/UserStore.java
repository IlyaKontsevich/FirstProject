package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class UserStore {

    public void addInfo(Map<String, User> users) throws SQLException, ClassNotFoundException {
        String name = "kontsevich";
        String password = "333498316";
        String url = "jdbc:postgresql://127.0.0.1:5432/test_db";
        Class.forName ("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(url,name,password);
             Statement statement = connection.createStatement()){
            statement.executeUpdate("drop table users");
            statement.executeUpdate("CREATE TABLE users(id int NOT NULL, name varchar NOT NULL);");
            for (User user : users.values()){
                int id = user.getId();
                String userName = user.getName();
                statement.executeUpdate("INSERT INTO users (id, name) VALUES ('"+ id +"', '"+ userName +"');");
            }

        }
    }

    public int getMaxId() throws SQLException, ClassNotFoundException {
        Map<String, User> users = new HashMap();
        users = getInfo();
        int maxId = -1;
        for (User user : users.values()){
            if (user.getId() > maxId){
                maxId = user.getId();
            }
        }
        maxId++;
        return maxId;
    }
    public Map<String, User> getInfo() throws ClassNotFoundException, SQLException {
        String name = "kontsevich";
        String password = "333498316";
        String url = "jdbc:postgresql://127.0.0.1:5432/test_db";
        Class.forName ("org.postgresql.Driver");
        Map<String, User> users = new HashMap();

        try (Connection connection = DriverManager.getConnection(url,name,password);
             Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id int NOT NULL, name varchar NOT NULL);");
            ResultSet resultSet =  statement.executeQuery("select * from users");
            while (resultSet.next()){
                String userName = resultSet.getString(2);//read user name
                User user = new User(userName);//create new user
                user.setId(resultSet.getInt(1));
                users.put(user.getName(),user);//add user in Map
            }
        }
        return users;
    }
}
