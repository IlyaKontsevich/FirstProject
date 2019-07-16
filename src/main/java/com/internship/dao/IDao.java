package com.internship.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public interface IDao<T> {

    boolean add(T type) throws SQLException;

    T get(String name) throws SQLException;

    Collection<T> getAll() throws SQLException;

    boolean delete(String name) throws SQLException;

    Connection getConnection() throws SQLException;

    Statement getStatement() throws SQLException;

    void closeConnection(Connection connection);
}

