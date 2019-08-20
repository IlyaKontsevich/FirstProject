package com.internship.service;
import com.internship.dao.IUserDao;
import com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserDao dao;

    @Override
    public void update(User user) {
        dao.update(user);
    }

    public User add(User user) {
        return dao.add(user);
    }

    public Collection<User> getPage(Integer position, Integer pageSize, List<String> sortType, List<String> filter){
        /*HashMap<String,String> sort = new HashMap();
        HashMap<String,String> filters = new HashMap();
        for(String str: sortType){
            String[] parts = str.split(":");
            sort.put(parts[0],parts[1]);
        }
        for(String str: filter){
            String[] parts = str.split(":");
            sort.put(parts[0],parts[1]);
        }
        /*String sort;
        sort = " ORDER BY " + String.join(",", sortType);
        sort = sort.replace(":", " ");
        String filters;
        if(filter.size()!=1){
            if(filter.get(0).equals(""))
                filter.remove(0);
            filters = String.join("' AND ",filter);
            filters = "WHERE "+filters.replace(":","='")+"'";
        }else {
            if(filter.get(0).equals("") || filter.get(0).equals(";"))
                filters ="";
            else{
                filters = String.join("' AND ",filter);
                filters = "WHERE "+filters.replace(":","='")+"'";
            }
        }
        */

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
