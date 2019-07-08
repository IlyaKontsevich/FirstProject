package com.internship.model;

import java.util.*;


public class User {
    private String name;
    private List<Task> tasks = new ArrayList();



    public String GetName(){
        return name;
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }

    public void getInfo(){
        System.out.println("Name of user: " + name);

        System.out.println("User tasks: ");
        for (Task task : tasks){
            System.out.println(task.getName());
            System.out.println();
        }
    }

    public User(){
        Scanner scaner = new Scanner(System.in);

        System.out.println("Enter user name: ");
        name = scaner.nextLine();
    }

    public void addTask(Task task){
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
//fdgdfg
//1.Naming convention
//2.Pakets
//3.github .idea .out *.iml + ignore: campiled; IDE; build
//4.RAEDME.md
//store
//!
//Dao  CRUD user or task
//!
//Service
//!
//console controller + validation
//by.exapel.model.User
//          .console.M
//          .service.
//          .dao



//Task: user.id