package com.internship.dao;

import com.internship.model.User;

import java.util.Collection;
import java.util.List;

public interface IUserDao extends IDao<User> {
    Collection<User> getPage(Integer position, Integer pageSize, List<String> sortType, List<String> filter);
    Integer getSize();
    Collection getAll();
}
