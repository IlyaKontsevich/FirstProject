package com.internship.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;

public interface IDao<T> {

    boolean add(T type);

    T get(String name);

    Collection<T> getAll();

    boolean delete(String name);

    Connection getConnection();

    Statement getStatement();

    void closeConnection(Connection connection);
}

