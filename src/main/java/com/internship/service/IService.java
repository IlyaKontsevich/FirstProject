package com.internship.service;

import java.util.Collection;

public interface IService <T,U> {
    boolean addTask(T type,String name);

    boolean addUser(U type);

    boolean deleteUser(String name);

    boolean deleteTask(String name);

    T getTask(String name);

    U getUser(String name);

    Collection<T> getAllTasks();

    Collection<U> getAllUsers();
}
