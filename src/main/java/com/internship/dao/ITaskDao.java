package com.internship.dao;

import com.internship.model.Task;

import java.util.Collection;

public interface ITaskDao extends IDao<Task> {
    Collection<Task> getPage(Integer position,Integer userId);
    Integer getSize(Integer userId);
}
