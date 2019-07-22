package com.internship.service;

import java.util.Collection;

public interface IService<T> {
    T add(T type);

    boolean delete(String name);

    T get(String name);

    Collection<T> getAll();

}
