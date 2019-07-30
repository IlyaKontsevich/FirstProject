package com.internship.service;
import com.internship.dao.ITaskDao;
import com.internship.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class TaskService implements ITaskService{
    @Autowired
    private ITaskDao dao;

    @Override
    public Collection<Task> getPage(Integer position,Integer pageSize,Integer userId, String sortType) {
        return dao.getPage(position,pageSize,userId,sortType);
    }

    @Override
    public Integer update(Task task) {
        return dao.update(task);
    }

    @Override
    public Task add(Task task) {
        return dao.add(task);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public Task get(Integer id) {
        return dao.get(id);
    }

    @Override
    public Integer getSize(Integer userId) {
        return dao.getSize(userId);
    }

    @Override
    public Collection<Task> getAll() {
        return dao.getAll();
    }

}
