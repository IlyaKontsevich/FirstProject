package com.internship.service;

import com.internship.dao.*;
import com.internship.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

public class Service {
    TaskDao taskDao = new TaskDao();
    UserDao userDao = new UserDao();

    public boolean addTask(Task task,String name) throws IOException, ParseException {
        if (taskDao.get(task.getName()) != null){ // exist check
            return false;
        }
        if  (userDao.get(name) == null) {
            User user = new User(name);//create new user
            task.setUserName(user.getName());//add user for task
            user.addTask(task.getName()); //add task in users list
            userDao.add(user); // add user in list
        }else{
            User user;
            user = userDao.get(name);//get user
            user.addTask(task.getName());//add task in user list
            userDao.add(user);//add changed user
            task.setUserName(name); //add user for task
        }
        taskDao.add(task);
        return true;
    }

    public boolean addUser(User user) throws IOException{
        if (userDao.get(user.getName()) != null){ //exist check
            return false;
        }
        userDao.add(user);
        return true;
    }

    public void deleteUser(String name) throws IOException{
        userDao.delete(name);
    }

    public boolean deleteTask(String name) throws IOException, ParseException {
        if(taskDao.get(name) == null){
            return false;
        }
        else {
            User user;
            user = userDao.get(taskDao.get(name).getUserName());
            user.deleteUserTask(taskDao.get(name));//deleted task from users list
            userDao.add(user);
            taskDao.delete(name);
            return true;
        }
    }


    public Task getTask(String name) throws IOException, ParseException {
        return taskDao.get(name); }

    public User getUser(String name) throws IOException{
        return userDao.get(name); }

    public Collection<Task> getAllTasks() throws IOException, ParseException {
        return taskDao.getAll(); }

    public Collection<User> getAllUsers() throws IOException{
        return userDao.getAll(); }

}
