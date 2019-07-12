package com.internship.model;

import java.util.*;


public class User {
    private String name;
    private List<String> tasks = new ArrayList();

    public String getName(){
        return name;
    }

    public List<String> getTasks(){
        return tasks;
    }

    public String getTask(int i){
        return tasks.get(i);
    }

    public void deleteUserTask(Task task){
        tasks.remove(task.getName());
    }

    public User(String name){
        this.name = name;
    }

    public void addTask(String taskName) {
        tasks.add(taskName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(tasks, user.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tasks);
    }
}
