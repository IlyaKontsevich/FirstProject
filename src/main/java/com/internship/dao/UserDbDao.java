package com.internship.dao;

import com.internship.model.User;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UserDbDao implements IDao<User> {

    public UserDbDao() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User add(User user){
        try {
            PreparedStatement preparedStatement = getStatement("INSERT INTO  users (name) VALUES  (?)");
            preparedStatement.setString(1,user.getName());
            int rez = preparedStatement.executeUpdate();
            closeConnection(getConnection());
            if (rez == 0) {
                return null;
            } else {
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String name){
        try {
            PreparedStatement preparedStatement = getStatement("DELETE FROM users WHERE name = ?");
            preparedStatement.setString(1,name);
            int rez = preparedStatement.executeUpdate();
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
            ResultSet resultSet = getStatement("Select * from users").executeQuery();
            while (resultSet.next()) {
                users.put(setUserInfo(resultSet).getName(),setUserInfo(resultSet));
            }
            closeConnection(getConnection());
            return users.values();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users.values();
    }

    private User setUserInfo(ResultSet resultSet){
        try {
            String userName = resultSet.getString(2);//read user name
            User user = new User(userName);//create new user
            user.setId(resultSet.getInt(1));
            return user;
        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }

    public User get(String name){
        try {
            PreparedStatement preparedStatement =  getStatement("SELECT id, name FROM users WHERE name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                closeConnection(getConnection());
                return null;
            } else {
                User user = setUserInfo(resultSet);
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

    private PreparedStatement getStatement(String sql){
        try {
            return getConnection().prepareStatement(sql);
        }catch (SQLException e){
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
