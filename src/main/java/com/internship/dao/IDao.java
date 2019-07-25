package com.internship.dao;

import java.util.Collection;

public interface IDao<T> {

    Collection<T> getPage(Integer position);

    Integer getSize();

    T add(T type);

    T get(Integer id);

    Collection<T> getAll();

    boolean delete(Integer id);
}

