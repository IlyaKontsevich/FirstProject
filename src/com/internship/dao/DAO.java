package com.internship.dao;

import com.internship.model.*;
import com.internship.store.*;

import java.util.*;

public class DAO {
    Store store = new Store();
    public void addTask(Task task){
        store.tasks.put(task.getName(), task);
        System.out.println("Task successfully added");
        //users.add(tasks.get(tasks.size() - 1).GetUser());
    }

    public void addUser(User user){
        store.users.put(user.GetName() ,user);
        System.out.println("User successfully added");
    }

    public void deleteUser(String name){
        store.users.remove(name);
        System.out.println("User" + name + "successfully deleted");
    }

    public void deleteTask(String name){
        store.tasks.remove(name);
        System.out.println("Task" + name + "successfully deleted");
    }

    public Task getTask(String name){ return store.tasks.get(name);    }

    public User getUser(String name){ return store.users.get(name); }

    public int getTaskSize(){ return store.tasks.size(); }

    public int getUserSize(){ return store.users.size(); }

    public Collection<Task> getTasks(){ return store.tasks.values(); }

    public Collection<User> getUsers(){ return store.users.values();
    }

}
