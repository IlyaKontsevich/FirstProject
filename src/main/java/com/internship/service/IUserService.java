package com.internship.service;

import com.internship.model.User;

import java.util.Collection;
import java.util.List;

public interface IUserService extends IService<User>{
    Collection<User> getPage(Integer position, Integer pageSize, List<String> sortType,List<String> filter);
    Integer getSize();
}
