package com.internship.service;

import com.internship.dao.*;
import com.internship.model.*;

import java.util.Collection;

public class Service {
    TaskDao taskDao = new TaskDao();
    UserDao userDao = new UserDao();

    public boolean addTask(Task task,String name){
        if (taskDao.get(task.getName()) != null){ // exist check
            return false;
        }
        if  (userDao.get(name) == null) {
            task.addUser(new User(name));//add user for task
            userDao.add(task.getUser()); // add user in list
            userDao.get(name).addTask(task); //add task in users list
        }else{
            task.addUser(userDao.get(name)); //add user for task
            userDao.get(name).addTask(task); //add task in user list
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

    public void deleteUser(String name){
        userDao.delete(name);
    }

    public boolean deleteTask(String name){
        if(taskDao.get(name) == null){
            return false;
        }
        else {
            taskDao.get(name).getUser().deleteUserTask(taskDao.get(name));//deleted task from users list
            taskDao.delete(name);
            return true;
        }
    }


    public Task getTask(String name){ return taskDao.get(name); }

    public User getUser(String name){ return userDao.get(name); }

    public Collection<Task> getAllTasks(){ return taskDao.getAll(); }

    public Collection<User> getAllUsers() { return userDao.getAll(); }
}
