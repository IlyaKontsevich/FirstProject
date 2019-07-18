package com.internship.dao;

import com.internship.model.User;
import com.internship.store.UserStore;

import java.util.Collection;
import java.util.Map;


public class UserFileDao implements IDao<User> {

    UserStore store = new UserStore();

    public User add(User user){
        Map<String, User> map = store.getInfo();
        map.put(user.getName(),user);
        store.addInfo(map);
        return user;
    }

    public int getMaxId(){
        return store.getMaxId();
    }

    public boolean delete(String name){
        Map<String, User> map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
        return true;
    }

    public Collection<User> getAll(){
        return store.getInfo().values();}

    public User get(String name){
        return store.getInfo().get(name); }


}
