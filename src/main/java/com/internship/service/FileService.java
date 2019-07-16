package com.internship.service;

import com.internship.dao.*;
import com.internship.model.*;

import java.util.Collection;

public class FileService implements IService<Task, User>{
    TaskFileDao taskDao = new TaskFileDao();
    UserFileDao userDao = new UserFileDao();

    public boolean addTask(Task task,String name){
        if (taskDao.get(task.getName()) != null){ // exist check
            return false;
        }
        if  (userDao.get(name) == null) {
            User user = new User(name);//create new user
            user.setId(userDao.getMaxId());//set user Id
            task.setUserId(user.getId());//add user ID for task
            userDao.add(user); // add user in list
        }else{
            task.setUserId(userDao.get(name).getId()); //add user ID for task
        }
        task.setId(taskDao.getMaxId());
        taskDao.add(task);
        return true;
    }

    public boolean addUser(User user){
        if (userDao.get(user.getName()) != null){ //exist check
            return false;
        }
        user.setId(userDao.getMaxId());//add user Id
        userDao.add(user);
        return true;
    }

    public boolean deleteUser(String name){
        for (Task task : getAllTasks()){
            if (task.getUserId() == getUser(name).getId()){
                deleteTask(task.getName());
            }
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
