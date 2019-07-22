package com.internship.dao;

import com.internship.model.Task;
import com.internship.model.User;
import com.internship.store.IStore;
import com.internship.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
@Profile("fileSystem")
public class UserFileDao implements IDao<User> {
    @Autowired
    private IStore<User> store;
    @Autowired
    private TaskFileDao taskDao;//need for delete user tasks

    public User add(User user) {
        user.setId(getMaxId());//set user id
        Map<String, User> map = store.getInfo();//get all users in Map
        map.put(user.getName(), user);//add user in map
        store.addInfo(map);//return Map in file
        return get(user.getName());
    }

    private int getMaxId() {
        return store.getMaxId();
    }

    public boolean delete(String name) {
        if (taskDao.getAll().size() != 0)//delete users task
            for (Task task : taskDao.getAll()) {
                if (task.getUserId() == get(name).getId()) {
                    taskDao.delete(task.getName());
                }
            }
        Map<String, User> map = store.getInfo();
        map.remove(name);
        store.addInfo(map);
        return true;
    }

    public Collection<User> getAll() {
        return store.getInfo().values();
    }

    public User get(String name) {
        return store.getInfo().get(name);
    }


}
