package com.internship.dao;

import com.internship.model.User;
import com.internship.store.UserStore;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;


public class UserFileDao implements IDao<User>{

    UserStore store = new UserStore();

    public boolean add(User user){
        Map<String, User> map = store.getInfo();
        map.put(user.getName(),user);
        store.addInfo(map);
        return true;
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

    public Connection getConnection(){
        return null;
    }

    public Statement getStatement(){
        return null;
    }

    public void closeConnection(Connection connection) {

    }

    public Collection<User> getAll(){
        return store.getInfo().values();}

    public User get(String name){
        return store.getInfo().get(name); }


}
