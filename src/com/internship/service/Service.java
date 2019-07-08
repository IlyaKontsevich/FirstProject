package com.internship.service;

import com.internship.dao.*;
import com.internship.model.*;

public class Service {
    DAO dao = new DAO();

    public void addTask(Task task){
        while (dao.getTask(task.getName()) != null){
            System.out.println("A task with the same name already exists. Pleas input new task");
            task = new Task();
        }
        if (dao.getUser(task.GetUser().GetName()) != null){
            dao.getUser(task.GetUser().GetName()).addTask(task);// add task to users list
        }
        else{
            dao.addUser(task.GetUser());
        }
        dao.addTask(task);
    }

    public void addUser(User user){
        while (dao.getUser(user.GetName()) != null){
            System.out.println("A user with the same name already exists. Pleas input new user");
            user = new User();
        }
        dao.addUser(user);
    }

    public void deleteUser(String name){
        if(dao.getUser(name) == null){
            System.out.println("A user with the same name don't exists");
        }
        else {
            for (Task task : dao.getUser(name).getTasks()){
                if (dao.getTask(task.getName()) != null) {
                    System.out.print("Delete users task:  ");
                    dao.deleteTask(task.getName());
                }
            }
            dao.deleteUser(name);
        }
    }

    public void deleteTask(String name){
        if(dao.getTask(name) == null){
            System.out.println("A task with the same name don't exists");
        }
        else {
            dao.getTask(name).GetUser().deleteTask(dao.getTask(name));//deleted task from users list
        }
        dao.deleteTask(name);
    }


    public void getTask(String name){
        if(dao.getTask(name) == null){
            System.out.println("A task with the same name don't exists");
        }
        else {
            dao.getTask(name).GetInfo();
        }
    }

    public void getUser(String name){
        if(dao.getUser(name) == null){
            System.out.println("A user with the same name don't exists");
        }
        else {
            dao.getUser(name).getInfo();
        }
    }

    public void getAllTasks(){
        if (dao.getTaskSize() != 0 ){
            for (Task task : dao.getTasks()){
                task.GetInfo();
            }
        } else {
            System.out.println("Empty list");
        }
    }

    public void getAllUsers() {
        if (dao.getUserSize() != 0) {
            for (User user : dao.getUsers()) {
                user.getInfo();
            }
        } else {
            System.out.println("Empty list");
        }
    }
}
