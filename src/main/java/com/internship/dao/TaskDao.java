package com.internship.dao;

import com.internship.model.*;
import com.internship.store.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class TaskDao {
    TaskStore store = new TaskStore();

    public void add(Task task) throws ParseException, SQLException, ClassNotFoundException {
     Map<String, Task> map = new HashMap();
     map = store.getInfo();
     map.put(task.getName(),task);
     store.addInfo(map);
    }

    public void delete(String name) throws ParseException, SQLException, ClassNotFoundException {
        Map<String, Task> map = new HashMap();
        map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
    }

    public int getMaxId() throws ParseException, SQLException, ClassNotFoundException {
        return store.getMaxId();
    }

    public Task get(String name) throws ParseException, SQLException, ClassNotFoundException {
        return store.getInfo().get(name);
    }

    public Collection<Task> getAll() throws ParseException, SQLException, ClassNotFoundException {
        return store.getInfo().values(); }

}
