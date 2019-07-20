package com.internship.service;

import com.internship.dao.TaskFileDao;
import com.internship.dao.UserFileDao;
import com.internship.model.Task;
import com.internship.model.User;

import java.util.Collection;

public class TaskFileService implements IService<Task> {
    private TaskFileDao dao;

    public TaskFileService(TaskFileDao dao) {
        this.dao = dao;
    }
    public Task add(Task task){
        for (Task task2 : dao.getAll()){
            if(task.getUserId() == task2.getUserId()){
                if(task.getName().equals(task2.getName())){
                    return null;
                }
            }
        }
        task.setId(dao.getMaxId());
        return dao.add(task);
    }

    public boolean delete(String name){
        if(dao.get(name) == null){
            return false;
        }else {
            dao.delete(name);
            return true;
        }
    }

    public Task get(String name){
        return dao.get(name); }

    public Collection<Task> getAll(){
        return dao.getAll(); }
}
