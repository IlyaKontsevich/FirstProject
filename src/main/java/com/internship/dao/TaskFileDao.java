package com.internship.dao;

import com.internship.model.Task;
import com.internship.store.TaskStore;

import java.util.Collection;
import java.util.Map;

public class TaskFileDao implements IDao<Task> {
    private TaskStore store = new TaskStore();

    public Task add(Task task){
        Map<String, Task> map = store.getInfo();
        map.put(task.getName(),task);
        store.addInfo(map);
        return task;
    }

    public boolean delete(String name){
        Map<String, Task> map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
        return true;
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
