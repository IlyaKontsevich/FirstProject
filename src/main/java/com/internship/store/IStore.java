package com.internship.store;

import java.util.Map;

public interface IStore<T> {
    void addInfo(Map<Integer, T> type);

    int getMaxId();

    Map<Integer, T> getInfo();
}
