package com.internship.dao;

import com.internship.model.Task;

import java.util.Collection;
import java.util.List;

public interface ITaskDao extends IDao<Task> {
    Collection<Task> getPage(Integer position, Integer userId, Integer pageSize, List<String> sortType, List<String> filters);
    Integer getSize(Integer userId);
    Collection getAll(Integer userId);
}
