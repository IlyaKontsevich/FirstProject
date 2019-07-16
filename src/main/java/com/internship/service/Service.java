package com.internship.service;

import com.internship.dao.*;
import com.internship.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

public class Service {
    TaskDao taskDao = new TaskDao();
    UserDao userDao = new UserDao();

    public boolean addTask(Task task,String name) throws SQLException{
        if (taskDao.get(task.getName()) != null){ // exist check
            return false;
        }
        if  (userDao.get(name) == null) {
            User user = new User(name);//create new user
            userDao.add(user);
            task.setUserId(userDao.get(name).getId());//add user ID for task
        }else{
            task.setUserId(userDao.get(name).getId()); //add user ID for task
        }
        taskDao.add(task);
        return true;
    }

    public boolean addUser(User user) throws SQLException{
        if (userDao.get(user.getName()) != null){ //exist check
            return false;
        }
        userDao.add(user);
        return true;
    }

    public void deleteUser(String name) throws SQLException{
        userDao.delete(name);
    }

    public boolean deleteTask(String name) throws SQLException{
        if(taskDao.get(name) == null){
            return false;
        }else {
            taskDao.delete(name);
            return true;
        }
    }

    public Task getTask(String name) throws SQLException{
        return taskDao.get(name); }

    public User getUser(String name) throws SQLException{
        return userDao.get(name); }

    public Collection<Task> getAllTasks() throws SQLException{
        return taskDao.getAll(); }

    public Collection<User> getAllUsers() throws SQLException{
        return userDao.getAll(); }

}
