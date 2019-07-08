package com.internship.model;

import java.util.*;


public class User {
    private String name;
    private Map<String,Task> tasks = new HashMap();



    public String GetName(){
        return name;
    }

    public Collection<Task> getTasks(){
        return tasks.values();
    }

    public void deleteUserTask(String name){
        tasks.remove(name);
    }

    public void getInfo(){
        System.out.println("Name of user: " + name);

        System.out.print("User tasks: ");
        for (Task task : tasks.values()){
            System.out.print(task.getName() + "   ");
        }
        System.out.println(" ");
    }

    public User(String name){
        this.name = name;
    }

    public void addTask(Task task){
        tasks.put(task.getName(), task);
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