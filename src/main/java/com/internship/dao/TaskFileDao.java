package com.internship.dao;

import com.internship.model.Task;
import com.internship.store.IStore;
import com.internship.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("fileSystem")
public class TaskFileDao implements ITaskDao {
    @Autowired
    private IStore<Task> store;

    @Override
    public Collection<Task> getPage(Integer position) {
        Collection<Task> tasks = new ArrayList();
        ArrayList<Task> tasks2 = new ArrayList();
        tasks2.addAll(getAll());
        tasks.add(tasks2.get(position));
        if(position+1 < tasks2.size())
        tasks.add(tasks2.get(position+1));
        if(position+2 < tasks2.size())
        tasks.add(tasks2.get(position+2));
        return tasks;
    }

    @Override
    public Integer getSize() {
        return getAll().size();
    }

    public Task add(Task task) {
        task.setId(getMaxId());//set task id
        Map<Integer, Task> map = store.getInfo();//get all tasks in Map
        map.put(task.getId(), task);//add task in this Map
        store.addInfo(map);//return Map in file
        return get(task.getId());
    }

    public boolean delete(Integer id) {
        Map<Integer, Task> map = store.getInfo();//get all tasks in Map
        map.remove(id);//remove task from Map
        store.addInfo(map);//return Map in file
        return true;
    }

    private int getMaxId() {
        return store.getMaxId();
    }

    public Task get(Integer id) {
        return store.getInfo().get(id);
    }


    public Collection<Task> getAll() {
        return store.getInfo().values();
    }
}
