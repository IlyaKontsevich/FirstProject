package com.internship.service;

import com.internship.dao.ITaskDao;
import com.internship.model.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TaskService implements ITaskService{
    @Autowired
    @Qualifier("taskHibernateDao")
    private ITaskDao dao;
    @Autowired
    private IUserService service;
    private static final Logger log = Logger.getLogger(TaskService.class);

    @Override
    public Collection<Task> getPage(Integer position, Integer pageSize, Integer userId, List<String> sortType,List<String> filter){
        log.info("Get task page");
        if(filter.get(0).equals("")){
            filter.remove(0);
        }
        if(position != 1) {
            position = position + pageSize - 2;
        }else{
            position = position-1;
        }
        return dao.getPage(position,userId,pageSize,sortType,filter);
    }

    @Override
    public void update(Task task)
    {
        log.info("Update task");
        dao.update(task);
    }

    @Override
    public Task add(Task task) {
        log.info("Add task");
        return dao.add(task);
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete task");
        dao.delete(id);
    }

    @Override
    public Task get(Integer id) {
        log.info("Get task");
        return dao.get(id);
    }


    @Override
    public Integer getSize(Integer userId) {
        return dao.getSize(userId);
    }

    @Override
    public Collection<Task> getAll() {
        return dao.getAll(1);
    }

}
