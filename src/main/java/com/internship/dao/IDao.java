package com.internship.dao;

import java.util.Collection;

public interface IDao<T> {

    T add(T type);

    T get(String name);

    Collection<T> getAll();

    boolean delete(String name);

}

