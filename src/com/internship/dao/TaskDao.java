package com.internship.dao;

import com.internship.model.*;
import com.internship.store.*;

import java.util.*;

public class TaskDao {
    TaskStore store = new TaskStore();
    public void add(Task task){ store.tasks.put(task.getName(), task); }

    public void delete(String name){ store.tasks.remove(name); }

    public Task get(String name){ return store.tasks.get(name); }

    public Collection<Task> getAll(){ return store.tasks.values(); }

}
//doa do 2class
//input uotput, only in console
//interfese or abstract, try creat
//3 !!!! store do in file !!!!!
//chek style idea
//4 JAR/VAR  JAR+JAR, module
//avatar in skype
//