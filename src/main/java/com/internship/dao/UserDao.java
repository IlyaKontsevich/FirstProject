package com.internship.dao;

import com.internship.model.User;
import com.internship.store.UserStore;

import java.io.IOException;
import java.sql.*;
import java.util.*;


public class UserDao implements IDao<User>{
    public boolean add(User user) throws SQLException{
        int rez = getStatement().executeUpdate("INSERT INTO  users (name) VALUES  ('" + user.getName() + "')");
        closeConnection(getConnection());
        if (rez == 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean delete(String name) throws SQLException{
        int rez = getStatement().executeUpdate("DELETE FROM users WHERE name = '" + name + "'");
        closeConnection(getConnection());
        if (rez == 0){
            return false;
        }else{
            return true;
        }
    }

    public Collection<User> getAll() throws SQLException {
        Map<String, User> users = new HashMap();
        ResultSet resultSet =  getStatement().executeQuery("select * from users");
        while (resultSet.next()){
            String userName = resultSet.getString(2);//read user name
            User user = new User(userName);//create new user
            user.setId(resultSet.getInt(1));
            users.put(user.getName(),user);//add user in Map
        }
        closeConnection(getConnection());
        return users.values();
    }

    public User get(String name) throws SQLException {
        ResultSet resultSet = getStatement().executeQuery("SELECT id FROM users WHERE name = '" + name + "'");
        if (!resultSet.next()){
            closeConnection(getConnection());
            return null;
        }else{
            User user = new User(name);
            user.setId(resultSet.getInt("id"));
            closeConnection(getConnection());
            return user;
        }
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test_db", "kontsevich", "333498316");
    }

    public Statement getStatement() throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");

        return statement;
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
