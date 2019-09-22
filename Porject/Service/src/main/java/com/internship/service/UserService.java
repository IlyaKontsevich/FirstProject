package com.internship.service;
import com.internship.dao.IUserDao;
import com.internship.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class UserService implements IUserService{
    @Autowired
    private IUserDao dao;
    private static final Logger log = Logger.getLogger(UserService.class);

    @Override
    public void update(User user) {
        log.info("Update user");
        dao.update(user);
    }

    public User add(User user)
    {
        log.info("Add user");
        return dao.add(user);
    }

    public Collection<User> getPage(Integer position, Integer pageSize, List<String> sortType, List<String> filter){
        log.info("Get user page");
        if(position != 1) {
            position = position + pageSize - 2;
        }else{
            position = position-1;
        }
        if(filter.get(0).equals("")){
            filter.remove(0);
        }
        return dao.getPage(position,pageSize,sortType,filter);
    }

    public void delete(Integer id) {
        log.info("Delete user");
        dao.delete(id);
    }

    public User get(Integer id)
    {
        log.info("Get user");
        return dao.get(id);
    }


    public Integer getSize() {
        return dao.getSize();
    }

    @Override
    public User getByEmail(String email) {
        return dao.getByEmail(email);
    }

    public Collection<User> getAll() {
        return dao.getAll();
    }
}
