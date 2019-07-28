package com.internship.service;

import java.util.Collection;

public interface IService<T> {
    Integer update(T type);
    T add(T type);
    void delete(Integer id);
    T get(Integer id);
    Collection<T> getAll();
}
