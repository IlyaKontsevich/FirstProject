package com.internship.console;

import java.util.*;
import  com.internship.model.*;
import com.internship.service.Service;

public class ConsoleApp {
    public static void main(String[] Args){
        ConsoleApp apllication = new ConsoleApp();

        apllication.scan();
        System.out.println("EXIT");
    }


     public void output() {

        System.out.println();
        System.out.println("                Console application");
        System.out.println("    1.Add task");
        System.out.println("    2.Add user");
        System.out.println("    3.Output tasks");
        System.out.println("    4.Output Users");
        System.out.println("    5.Find task");
        System.out.println("    6.Find user");
        System.out.println("    7.Delete task");
        System.out.println("    8.Delete User");
        System.out.println("    9.exit");
    }

    public void scan(){
        int symbol = 0;
        Scanner scaner = new Scanner(System.in);
        Service service = new Service();

        while (symbol != 9) {
            output();
            symbol = scaner.nextInt();
            switch(symbol)
            {
                case 1:
                    service.addTask(new Task());
                    break;

                case 2:
                    service.addUser(new User());
                    break;

                case 3:

                    service.getAllTasks();
                    break;

                case 4:
                    service.getAllUsers();
                    break;

                case 5:
                    System.out.println("Enter name of task: ");
                    service.getTask(scaner.nextLine());
                    break;

                case 6:
                    System.out.println("Enter name of user: ");
                    service.getUser(scaner.nextLine());
                    break;

                case 7:
                    System.out.println("Enter taskname to delete: ");
                    service.deleteTask(scaner.nextLine());
                    break;

                case 8:
                    System.out.println("Enter username to delete: ");
                    service.deleteUser(scaner.nextLine());
                    break;

                case 9:
                    break;


                default:
                error();
            }
        }
    }

    public void error(){
        System.out.println("Error.");
    }
}
