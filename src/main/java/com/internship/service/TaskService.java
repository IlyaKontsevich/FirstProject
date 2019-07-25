package com.internship.service;

import com.internship.dao.IDao;
import com.internship.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class TaskService implements IService<Task> {
    @Autowired
    private IDao<Task> dao;

    @Override
    public Collection<Task> getPage(Integer position) {
        return dao.getPage(position);
    }

    public Task add(Task task) {
        for (Task task2 : dao.getAll()) {
            if (task.getUserId() == task2.getUserId()) {
                if (task.getName().equals(task2.getName())) {
                    return null;
                }
            }
        }
        return dao.add(task);
    }


    public boolean delete(Integer id) {
        if (dao.get(id) == null) {
            return false;
        } else {
            dao.delete(id);
            return true;
        }
    }

    public Task get(Integer id) {
        return dao.get(id);
    }

    @Override
    public Integer getSize() {
        return dao.getSize();
    }


    public Collection<Task> getAll() {
        return dao.getAll();
    }

    @Override
    public Collection<Task> getAllById(Integer id) {
        Collection<Task> tasks = new ArrayList();
        for (Task task : dao.getAll()) {
            if (task.getUserId() == id) {
                tasks.add(task);
            }
        }
        return tasks;
    }

}
