package com.internship.dao;

import com.internship.model.User;

import java.util.Collection;

public interface IUserDao extends IDao<User> {
    Collection<User> getPage(Integer position, Integer pageSize, String sortType);
    Integer getSize();
}
