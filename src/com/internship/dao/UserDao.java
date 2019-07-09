package com.internship.dao;

import com.internship.model.User;
import com.internship.store.UserStore;

import java.util.Collection;

public class UserDao {

    UserStore store = new UserStore();

    public void add(User user){ store.users.put(user.getName() ,user); }

    public void delete(String name){ store.users.remove(name);}

    public Collection<User> getAll(){ return store.users.values();}

    public User get(String name){ return store.users.get(name); }

}
