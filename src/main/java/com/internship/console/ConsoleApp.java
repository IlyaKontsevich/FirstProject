package com.internship.console;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import  com.internship.model.*;
import com.internship.service.Service;

public class ConsoleApp {
    public static void main(String[] Args) throws IOException, ParseException {
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

    public void scan() throws IOException, ParseException {
        int symbol = 0;
        Scanner symbolScanner = new Scanner(System.in);
        Service service = new Service();

        while (symbol != 9) {
            String userName;
            String taskName;
            output();
            symbol = symbolScanner.nextInt();
            switch(symbol)
            {
                case 1:
                    taskName = inputTaskName();
                    userName = inputUserName();
                    while ( ! service.addTask(new Task(taskName,inputData()),userName) ){
                        System.out.println("A task with the same name already exists. Pleas input new task");
                        taskName = inputTaskName();
                        userName = inputUserName();
                    }
                    System.out.println("Task: " + taskName + " successfully added");
                    break;

                case 2:
                    userName = inputUserName();
                    while( ! service.addUser(new User(userName)) ){
                        System.out.println("A user with the same name already exists. Pleas input new user");
                        userName = inputUserName();
                    }
                    System.out.println("User: " + userName + " successfully added");
                    break;

                case 3:
                    if (service.getAllTasks().size() != 0 ){
                        for (Task task : service.getAllTasks()){
                            outputTaskInfo(task);
                        }
                    } else {
                        System.out.println("Empty task list");
                    }
                    break;

                case 4:
                    if (service.getAllUsers().size() != 0 ){
                        for (User user : service.getAllUsers()){
                            outputUserInfo(user);
                        }
                    } else {
                        System.out.println("Empty user list");
                    }
                    break;

                case 5:
                    taskName = inputTaskName();
                    if (service.getTask(taskName) == null){
                        System.out.println("A task with the same name don't exists");
                    }else {
                        outputTaskInfo(service.getTask(taskName));
                    }
                    break;

                case 6:
                    userName = inputUserName();
                    if (service.getUser(userName) == null){
                        System.out.println("A user with the same name don't exists");
                    }else {
                        outputUserInfo(service.getUser(userName));
                    }
                    break;

                case 7:
                    taskName = inputTaskName();
                    if ( ! service.deleteTask(taskName)){
                        System.out.println("A task with the same name don't exists");
                    }else {
                        System.out.println("Task: " + taskName + " successfully deleted");
                    }
                    break;

                case 8:
                    userName = inputUserName();
                    if ( service.getUser(userName) == null){
                        System.out.println("A user with the same name don't exists");
                    }else {
                        int size = service.getUser(userName).getTasks().size();
                        System.out.println("Delete " + size + " users tasks");
                        for (int i = 0; i < size; i++) {
                            service.deleteTask(service.getUser(userName).getTask(0));
                        }
                        service.deleteUser(userName);
                        System.out.println("User: " + userName + " successfully deleted");
                    }
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

    public Date inputData(){
        Scanner scaner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date deadline;

        while(true) {
            try {
                System.out.println("Enter task deadline in format: dd-MM-yyyy ");
                deadline = dateFormat.parse(scaner.next());
                break;
            } catch (ParseException e) {
                System.out.println("Date error. Please try again: ");
            }
        }
        return deadline;
    }

    public String inputTaskName(){
        Scanner nameScanner = new Scanner(System.in);
        String taskName;

        System.out.println("Enter name of task: ");
        taskName = nameScanner.nextLine();

        return taskName;
    }

    public String inputUserName(){
        Scanner nameScanner = new Scanner(System.in);
        String userName;

        System.out.println("Enter name of User: ");
        userName = nameScanner.nextLine();

        return userName;
    }

    public void outputTaskInfo(Task task){
        System.out.println("Name of task: " + task.getName());
        System.out.println("Task id: " + task.getId());
        System.out.println("Task deadline: " + task.getDeadline());
        System.out.println("Task user: " + task.getUserName() + "\n");
    }

    public void outputUserInfo(User user){
        System.out.println("User name: " + user.getName());
        System.out.print("User tasks: ");
        for (String userTask : user.getTasks()){
            System.out.print(userTask + "   ");
        }
        System.out.println(" ");
    }
}
