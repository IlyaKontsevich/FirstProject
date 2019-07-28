package com.internship.service;
import com.internship.dao.IUserDao;
import com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserDao dao;

    @Override
    public Integer update(User user) {
        return dao.update(user);
    }

    public User add(User user) {
        return dao.add(user);
    }

    public Collection<User> getPage(Integer position){
        return dao.getPage(position);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public User get(Integer id) {
        return dao.get(id);
    }


    public Integer getSize() {
        return dao.getSize();
    }

    public Collection<User> getAll() {
        return dao.getAll();
    }
}
