package com.internship.dao;

public interface IDao<T> {
    void update(T type);

    T add(T type);

    T get(Integer id);

    boolean delete(Integer id);
}

