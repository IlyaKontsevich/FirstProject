package com.internship.model;

import java.util.*;


public class User {
    private String name;
    private List<Task> tasks = new ArrayList();

    public String getName(){
        return name;
    }

    public List<Task> getTasks(){
        return tasks;
    }
    public Task getTask(int i){
        return tasks.get(i);
    }

    public void deleteUserTask(Task task){
        tasks.remove(task);
    }

    public User(String name){
        this.name = name;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
