package com.internship.dao;

import com.internship.model.*;
import com.internship.store.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

public class TaskFileDao implements IDao<Task>{
    TaskStore store = new TaskStore();

    public boolean add(Task task){
        Map<String, Task> map = store.getInfo();
        map.put(task.getName(),task);
        store.addInfo(map);
        return true;
    }

    public boolean delete(String name){
        Map<String, Task> map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
        return true;
    }

    public Connection getConnection() {
        return null;
    }

    public Statement getStatement(){
        return null;
    }

    public void closeConnection(Connection connection) {

    }

    public int getMaxId(){
        return store.getMaxId();
    }

    public Task get(String name){
        return store.getInfo().get(name);
    }

    public Collection<Task> getAll(){
        return store.getInfo().values(); }
}
