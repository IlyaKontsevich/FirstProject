package com.internship.service;

import com.internship.dao.IDao;
import com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IService<User> {
    @Autowired
    private IDao<User> dao;

    public User add(User user) {
        for (User user1 : dao.getAll()) {
            if (user1.getName().equals(user.getName())) {
                return null;
            }
        }return dao.add(user);
    }

    public Collection<User> getPage(Integer position){
        return dao.getPage(position);
    }

    public boolean delete(Integer id) {
        if (dao.get(id) == null) {
            return false;
        }
        dao.delete(id);
        return true;
    }

    public User get(Integer id) {
        return dao.get(id);
    }

    @Override
    public Integer getSize() {
        return dao.getSize();
    }

    public Collection<User> getAll() {
        return dao.getAll();
    }

    @Override
    public Collection<User> getAllById(Integer id) {
        return null;
    }

}
