package com.internship.console;

import java.util.*;
import  com.internship.model.*;

public class ConsoleApp {
    private List<Task> tasks = new ArrayList();
    private List<User> users = new ArrayList();

    public static void main(String[] Args){
        ConsoleApp apllication = new ConsoleApp();

        apllication.scan();
        System.out.println("EXIT");
    }


     public void output() {
        System.out.println();
        System.out.println("            Console application");
        System.out.println("1.Add task");
        System.out.println("2.Add user");
        System.out.println("3.Output task");
        System.out.println("4.Output User");
        System.out.println("5.exit");
    }

    public void scan(){
        int symbol = 0;
        Scanner scaner = new Scanner(System.in);

        while (symbol != 5) {
            output();
            symbol = scaner.nextInt();
            switch(symbol)
            {
                case 1:
                    addTask();
                    break;

                case 2:
                    addUser();
                    break;

                case 3:
                    showTask();
                    break;

                case 4:
                    showUser();
                    break;

                case 5:
                    break;


                default:
                error();
            }
        }
    }

    public void addTask(){
        tasks.add(new Task());
        users.add(tasks.get(tasks.size() - 1).GetUser());
    }


    public void addUser(){
        users.add(new User());
    }

    public void showUser(){
        for (User user : users){
            System.out.println(user.GetName());
        }
    }

    public void showTask(){
        for (Task Task : tasks){
            Task.GetInfo();
            System.out.println();
        }
    }

    public void error(){
        System.out.println("Error.");
    }
}
