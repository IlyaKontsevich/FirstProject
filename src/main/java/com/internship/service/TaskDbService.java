package com.internship.service;

import com.internship.dao.IDao;
import com.internship.dao.TaskDbDao;
import com.internship.model.Task;
import java.util.Collection;

public class TaskDbService implements IService<Task> {
    private TaskDbDao dao;

    public TaskDbService(TaskDbDao dao) {
        this.dao = dao;
    }

    public  Task add(Task task){
        for (Task task2 : dao.getAll()){
            if(task.getUserId() == task2.getUserId()){
                if(task.getName().equals(task2.getName())){
                    return null;
                }
            }
        }
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
