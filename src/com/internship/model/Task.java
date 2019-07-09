package com.internship.model;

import java.util.*;

public class Task {
    private int id;
    private String name;
    private User user;
    private Date deadline;


    public Task(String name, Date data) {
        this.name = name;
        this.deadline = data;
        id = hashCode();
    }

    public User getUser(){
        return user;
    }

    public String getName(){
        return name;
    }

    public Date getDeadline(){
        return deadline;
    }

    public int getId() { return id; }

    public void addUser(User user){
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                Objects.equals(user, task.user) &&
                Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user, deadline);
    }
}

//