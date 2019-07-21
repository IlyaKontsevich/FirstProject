package com.internship.service;
import com.internship.dao.UserDbDao;
import com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserDbService implements IService<User>{
    @Autowired
    private UserDbDao dao;

    /*@Autowired
    public UserDbService(UserDbDao dao) {
        this.dao = dao;
    }*/

    public User add(User user){
        if (get(user.getName()) != null){ //exist check
            return null;
        }
        return dao.add(user);
    }

    public boolean delete(String name){
        if (dao.get(name) == null){
            return false;
        }
        dao.delete(name);
        return true;
    }

    public User get(String name){
        return dao.get(name); }

    public Collection<User> getAll(){
        return dao.getAll(); }

}
