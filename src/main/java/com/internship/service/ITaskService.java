package com.internship.service;

import com.internship.model.Task;

import java.util.Collection;

public interface ITaskService extends IService<Task> {
    Collection<Task> getPage(Integer position,Integer pageSize ,Integer userId,String sortType);
    Integer getSize(Integer userId);
}
