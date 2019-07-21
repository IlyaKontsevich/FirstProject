package com.internship.service;
import com.internship.dao.TaskFileDao;
import com.internship.dao.UserFileDao;
import com.internship.model.Task;
import com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserFileService implements IService<User>{
    @Autowired
    private UserFileDao dao;

    /*@Autowired
    public UserFileService(UserFileDao dao) {
        this.dao = dao;
    }*/

    public User add(User user){
        if (get(user.getName()) != null){ //exist check
            return null;
        }
        user.setId(dao.getMaxId());
        return dao.add(user);
    }

    public boolean delete(String name){
        TaskFileDao taskDao = new TaskFileDao();
        if(taskDao.getAll().size()!= 0)
        for (Task task : taskDao.getAll()){
            if (task.getUserId() == get(name).getId()){
                taskDao.delete(task.getName());
            }
        }
        dao.delete(name);
        return true;
    }
    public User get(String name){
        return dao.get(name); }

    public Collection<User> getAll(){
        return dao.getAll(); }
}
