package com.internship.dao;

import java.util.Collection;

public interface IDao<T> {
    Integer update(T type);

    T add(T type);

    T get(Integer id);

    Collection<T> getAll();

    boolean delete(Integer id);
}

