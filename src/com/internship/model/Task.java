package com.internship.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Task {
    private int id;
    private String name;
    private User user;
    private Date deadline;


    public Task() {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Enter task name: ");
        name = scaner.nextLine();
        inputData();
        id = hashCode();
    }

    public void inputData(){
        Scanner scaner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        while(true) {
            try {
                System.out.println("Enter task deadline in format: dd-MM-yyyy ");
                deadline = dateFormat.parse(scaner.next());
                break;
            } catch (ParseException e) {
                System.out.println("Date error. Please try again: ");
            }
        }
    }

    public User GetUser(){
        return user;
    }

    public String getName(){
        return name;
    }

    public void addUser(User user){
        this.user = user;
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
