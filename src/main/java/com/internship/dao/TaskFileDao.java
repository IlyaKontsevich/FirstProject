package com.internship.dao;

import com.internship.model.Task;
import com.internship.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class TaskFileDao implements IDao<Task> {
    //@Autowired
    private TaskStore store = new TaskStore();

    public Task add(Task task){
        Map<String, Task> map = store.getInfo();
        map.put(task.getName(),task);
        store.addInfo(map);
        return get(task.getName());
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
