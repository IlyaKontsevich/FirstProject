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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Repository
@Profile("fileSystem")
public class UserFileDao implements IUserDao {
    @Autowired
    private IStore<User> store;
    @Autowired
    private TaskFileDao taskDao;//need for delete user tasks

    @Override
    public Collection<User> getPage(Integer position) {
        Collection<User> users = new ArrayList();
        ArrayList<User> users2 = new ArrayList();
        users2.addAll(getAll());
        users.add(users2.get(position));
        if(position+1 < users2.size())
        users.add(users2.get(position+1));
        if(position+2 < users2.size())
        users.add(users2.get(position+2));
        return users;
    }

    @Override
    public Integer getSize() {
        return getAll().size();
    }

    public User add(User user) {
        user.setId(getMaxId());//set user id
        Map<Integer, User> map = store.getInfo();//get all users in Map
        map.put(user.getId(), user);//add user in map
        store.addInfo(map);//return Map in file
        return get(user.getId());
    }

    private int getMaxId() {
        return store.getMaxId();
    }

    public boolean delete(Integer id) {
        if (taskDao.getAll().size() != 0)//delete users task
            for (Task task : taskDao.getAll()) {
                if (task.getUserId() == get(id).getId()) {
                    taskDao.delete(task.getId());
                }
            }
        Map<Integer, User> map = store.getInfo();
        map.remove(id);
        store.addInfo(map);
        return true;
    }

    public Collection<User> getAll() {
        return store.getInfo().values();
    }

    public User get(Integer id) {
        return store.getInfo().get(id);
    }


}
