package com.internship.service;

import com.internship.model.Task;

import java.util.Collection;

public interface ITaskService extends IService<Task> {
    Collection<Task> getPage(Integer position, Integer userId);
    Integer getSize(Integer userId);
}
