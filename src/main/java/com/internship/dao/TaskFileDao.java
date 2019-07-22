package com.internship.dao;

import com.internship.model.Task;
import com.internship.store.IStore;
import com.internship.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
@Profile("fileSystem")
public class TaskFileDao implements IDao<Task> {
    @Autowired
    private IStore<Task> store;

    public Task add(Task task) {
        task.setId(getMaxId());//set task id
        Map<String, Task> map = store.getInfo();//get all tasks in Map
        map.put(task.getName(), task);//add task in this Map
        store.addInfo(map);//return Map in file
        return get(task.getName());
    }

    public boolean delete(String name) {
        Map<String, Task> map = store.getInfo();//get all tasks in Map
        map.remove(name);//remove task from Map
        store.addInfo(map);//return Map in file
        return true;
    }

    private int getMaxId() {
        return store.getMaxId();
    }

    public Task get(String name) {
        return store.getInfo().get(name);
    }

    public Collection<Task> getAll() {
        return store.getInfo().values();
    }
}
