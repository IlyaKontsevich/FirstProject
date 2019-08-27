package com.internship.service;

import com.internship.model.Task;

import java.util.Collection;
import java.util.List;

public interface ITaskService extends IService<Task> {
    Collection<Task> getPage(Integer position, Integer pageSize, Integer userId, List<String> sortType, List<String> filter);
    Integer getSize(Integer userId);
}
