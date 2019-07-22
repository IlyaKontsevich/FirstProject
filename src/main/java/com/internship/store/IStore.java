package com.internship.store;

import java.util.Map;

public interface IStore<T> {
    void addInfo(Map<String, T> type);

    int getMaxId();

    Map<String, T> getInfo();
}
