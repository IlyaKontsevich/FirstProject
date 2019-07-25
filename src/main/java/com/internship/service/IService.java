package com.internship.service;

import java.util.Collection;

public interface IService<T> {
    Collection<T> getPage(Integer position);

    T add(T type);

    boolean delete(Integer id);

    T get(Integer id);

    Integer getSize();

    Collection<T> getAll();

    Collection<T> getAllById(Integer id);
}
