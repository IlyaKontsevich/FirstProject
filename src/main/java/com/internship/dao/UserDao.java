package com.internship.dao;

import com.internship.model.User;
import com.internship.store.UserStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


public class UserDao {

    UserStore store = new UserStore();

    public void add(User user) throws IOException, SQLException, ClassNotFoundException {
        Map<String, User> map = new HashMap();
        map = store.getInfo();
        map.put(user.getName(),user);
        store.addInfo(map);
    }

    public int getMaxId() throws IOException, SQLException, ClassNotFoundException {
        return store.getMaxId();
    }

    public void delete(String name) throws IOException, SQLException, ClassNotFoundException {
        Map<String, User> map = new HashMap();
        map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
    }

    public Collection<User> getAll() throws SQLException, ClassNotFoundException {
        return store.getInfo().values();}

    public User get(String name) throws SQLException, ClassNotFoundException {
        return store.getInfo().get(name); }


}
