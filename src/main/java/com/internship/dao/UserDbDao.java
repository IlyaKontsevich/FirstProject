package com.internship.dao;

import com.internship.model.User;
import java.sql.*;
import java.util.*;


public class UserDbDao implements IDao<User>{
    public boolean add(User user){
        try {
            int rez = getStatement().executeUpdate("INSERT INTO  users (name) VALUES  ('" + user.getName() + "')");
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
            int rez = getStatement().executeUpdate("DELETE FROM users WHERE name = '" + name + "'");
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

    public Collection<User> getAll(){
        Map<String, User> users = new HashMap();
        try {
            ResultSet resultSet = getStatement().executeQuery("select * from users");
            while (resultSet.next()) {
                String userName = resultSet.getString(2);//read user name
                User user = new User(userName);//create new user
                user.setId(resultSet.getInt(1));
                users.put(user.getName(), user);//add user in Map
            }
            closeConnection(getConnection());
            return users.values();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users.values();
    }

    public User get(String name){
        try {
            ResultSet resultSet = getStatement().executeQuery("SELECT id FROM users WHERE name = '" + name + "'");
            if (!resultSet.next()) {
                closeConnection(getConnection());
                return null;
            } else {
                User user = new User(name);
                user.setId(resultSet.getInt("id"));
                closeConnection(getConnection());
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test_db", "kontsevich", "333498316");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Statement getStatement(){
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");

            return statement;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
