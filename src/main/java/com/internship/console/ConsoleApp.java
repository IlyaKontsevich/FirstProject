package com.internship.console;


import com.internship.model.Task;
import com.internship.model.User;
import com.internship.service.IService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

public class ConsoleApp {
    private static IService<Task, User> service;

    public ConsoleApp(IService<Task, User> service){
        this.service = service;
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
        String symbol = "";
        Scanner symbolScanner = new Scanner(System.in);

        while (!symbol.equals("9")) {
            String userName;
            String taskName;
            output();
            symbol = symbolScanner.nextLine();
            switch(symbol)
            {
                case "1":
                    taskName = inputTaskName();
                    userName = inputUserName();
                    while ( ! service.addTask(new Task(taskName,inputData()),userName) ){
                        System.out.println("A task with the same name already exists. Pleas input new task");
                        taskName = inputTaskName();
                        userName = inputUserName();
                    }
                    System.out.println("Task: " + taskName + " successfully added");
                    break;

                case "2":
                    userName = inputUserName();
                    while( ! service.addUser(new User(userName)) ){
                        System.out.println("A user with the same name already exists. Pleas input new user");
                        userName = inputUserName();
                    }
                    System.out.println("User: " + userName + " successfully added");
                    break;

                case "3":
                    if (service.getAllTasks().size() != 0 ){
                        for (Task task : service.getAllTasks()){
                            outputTaskInfo(task, service.getAllUsers());
                        }
                    } else {
                        System.out.println("Empty task list");
                    }
                    break;

                case "4":
                    if (service.getAllUsers().size() != 0 ){
                        for (User user : service.getAllUsers()){
                            outputUserInfo(user, service.getAllTasks());
                        }
                    } else {
                        System.out.println("Empty user list");
                    }
                    break;

                case "5":
                    taskName = inputTaskName();
                    if (service.getTask(taskName) == null){
                        System.out.println("A task with the same name don't exists");
                    }else {
                        outputTaskInfo(service.getTask(taskName), service.getAllUsers());
                    }
                    break;

                case "6":
                    userName = inputUserName();
                    if (service.getUser(userName) == null){
                        System.out.println("A user with the same name don't exists");
                    }else {
                        outputUserInfo(service.getUser(userName), service.getAllTasks());
                    }
                    break;

                case "7":
                    taskName = inputTaskName();
                    if ( ! service.deleteTask(taskName)){
                        System.out.println("A task with the same name don't exists");
                    }else {
                        System.out.println("Task: " + taskName + " successfully deleted");
                    }
                    break;

                case "8":
                    userName = inputUserName();
                    if ( service.getUser(userName) == null){
                        System.out.println("A user with the same name don't exists");
                    }else {
                        service.deleteUser(userName);
                        System.out.println("\nUser: " + userName + " successfully deleted");
                    }
                    break;

                case "9":
                    break;


                default:
                error();
            }
        }
    }

    public void error(){
        System.out.println("Error, please enter another number");
    }

    public LocalDate inputData(){
        Scanner scanner = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        String yearS, monthS,dayOfMontshS;
        System.out.println("Enter year of deadline");
        yearS = scanner.nextLine();
        while (!errorCheckForNumber(yearS) || (Integer.parseInt(yearS) < date.getYear())){
            System.out.println("Error,please enter another year");
            yearS = scanner.nextLine();
        }
        int year = Integer.parseInt(yearS);
        System.out.println("Enter month of deadline (1-12)");
        monthS = scanner.nextLine();
        while (!errorCheckForNumber(monthS) || ((year == date.getYear()) && (Integer.parseInt(monthS) < date.getMonthValue()))
                || Integer.parseInt(monthS) < 1 || Integer.parseInt(monthS) > 12){
            System.out.println("Error,please enter another month");
            monthS = scanner.nextLine();
        }
        int month = Integer.parseInt(monthS);
        System.out.println("Enter month of deadline (1-31)");
        dayOfMontshS = scanner.nextLine();
        while(!errorCheckForNumber(dayOfMontshS) || (year == date.getYear() && month == date.getMonthValue() && Integer.parseInt(dayOfMontshS)
                < date.getDayOfMonth()) || Integer.parseInt(dayOfMontshS) < 1 || Integer.parseInt(dayOfMontshS) > 31){
            System.out.println("Error,please enter another day");
            dayOfMontshS = scanner.nextLine();
        }
        int dayOfMonth = Integer.parseInt(dayOfMontshS);
        date = LocalDate.of(year,month,dayOfMonth);
        return date;
    }

    public boolean errorCheckForNumber(String date){
        if(date.chars().allMatch( Character::isDigit) && !date.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public String inputTaskName(){
        Scanner nameScanner = new Scanner(System.in);
        String taskName;
        System.out.println("Enter name of task: ");
        taskName = nameScanner.nextLine();

        while (taskName.length() == 0){
            System.out.println("Empty string, please enter new task name");
            taskName = inputTaskName();
        }
        taskName = taskName.replace(","," ");
        return taskName;
    }

    public String inputUserName(){
        Scanner nameScanner = new Scanner(System.in);
        String userName;

        System.out.println("Enter name of User: ");
        userName = nameScanner.nextLine();

        while (userName.length() == 0){
            System.out.println("Empty string, please enter new user name");
            userName = inputTaskName();
        }
        userName = userName.replace(","," ");
        return userName;
    }

    public void outputTaskInfo(Task task, Collection<User> users){
        System.out.println("Task id: " + task.getId());
        System.out.println("Name of task: " + task.getName());
        System.out.println("Task deadline: " + task.getDeadline());
        System.out.print("Task user: " );
        for (User user : users){
            if(user.getId() == task.getUserId()){
                System.out.println(user.getName() + "\n");
                break;
            }
        }
    }

    public void outputUserInfo(User user, Collection<Task> tasks){
        System.out.println("User name: " + user.getName());
        System.out.println("User id: " + user.getId());
        System.out.print("User tasks: ");
        for (Task task : tasks){
            if (user.getId() == task.getUserId()) {
                System.out.print(task.getName() + "   ");
            }
        }
        System.out.println("\n");
    }
}