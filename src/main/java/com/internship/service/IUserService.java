package com.internship.service;

import com.internship.model.User;

import java.util.Collection;

public interface IUserService extends IService<User>{
    Collection<User> getPage(Integer position);
    Integer getSize();
}
