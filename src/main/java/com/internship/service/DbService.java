package com.internship.service;

import com.internship.dao.*;
import com.internship.model.*;

import java.util.Collection;

public class DbService implements IService<Task, User>{
    TaskDbDao taskDao = new TaskDbDao();
    UserDbDao userDao = new UserDbDao();

    public  boolean addTask(Task task,String name){
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

    public boolean addUser(User user){
        if (userDao.get(user.getName()) != null){ //exist check
            return false;
        }
        userDao.add(user);
        return true;
    }

    public boolean deleteUser(String name){
        if (userDao.get(name) == null){
            return false;
        }
        userDao.delete(name);
        return true;
    }

    public boolean deleteTask(String name){
        if(taskDao.get(name) == null){
            return false;
        }else {
            taskDao.delete(name);
            return true;
        }
    }

    public Task getTask(String name){
        return taskDao.get(name); }

    public User getUser(String name){
        return userDao.get(name); }

    public Collection<Task> getAllTasks(){
        return taskDao.getAll(); }

    public Collection<User> getAllUsers(){
        return userDao.getAll(); }

}
