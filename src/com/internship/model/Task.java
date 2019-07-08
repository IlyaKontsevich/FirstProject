package com.internship.model;

import java.util.Objects;
import java.util.Scanner;

public class Task {
    private String id;
    private String name;
    private User user;
    private String deadline;


    public Task() {
        Scanner scaner = new Scanner(System.in);

        System.out.println("Enter task id: ");
        id = scaner.nextLine();
        System.out.println("Enter task name: ");
        name = scaner.nextLine();
        System.out.println("Enter task deadline: ");
        deadline = scaner.nextLine();
        this.user = new User();
        System.out.println("Task successfully add");
    }
    public User GetUser(){
        return user;
    }

    public void GetInfo(){
        System.out.println("Task id: " + id);
        System.out.println("Task name: " + name);
        System.out.println("Task deadline: " + deadline);
        System.out.println("User name: " + user.GetName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(name, task.name) &&
                Objects.equals(user, task.user) &&
                Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user, deadline);
    }
}
