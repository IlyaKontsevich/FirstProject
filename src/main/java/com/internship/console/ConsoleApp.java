package com.internship.console;


import com.internship.model.Task;
import com.internship.model.User;
import com.internship.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

@Component
public class ConsoleApp {
    @Autowired
    private IService<User> userService;
    @Autowired
    private IService<Task> taskService;

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

    public void scan() {
        String symbol = "";
        Scanner symbolScanner = new Scanner(System.in);

        while (!symbol.equals("9")) {
            User user;
            Task task;
            output();
            symbol = symbolScanner.nextLine();
            switch (symbol) {
                case "1":
                    task = inputTask();
                    user = inputUser();
                    if (userService.get(user.getName()) == null) {
                        user = userService.add(user);
                        task.setUserId(user.getId());
                        task.setDeadline(inputData());
                        task = taskService.add(task);
                        System.out.println("User: " + user.getName() + " with id: " + user.getId() + " successfully added");
                        System.out.println("Task: " + task.getName() + " with id: " + task.getId() + " successfully added");
                    } else {
                        user = userService.get(user.getName());
                        task.setUserId(user.getId());
                        task.setDeadline(inputData());
                        while ((task = taskService.add(task)) == null) {
                            System.out.println("Task with same name are exists, please enter new task: ");
                            task = inputTask();
                            task.setUserId(user.getId());
                            task.setDeadline(inputData());
                        }
                        System.out.println("Task: " + task.getName() + " with id: " + task.getId() + " successfully added");
                    }
                    break;

                case "2":
                    user = inputUser();
                    while ((user = userService.add(user)) == null) {
                        System.out.println("A user with the same name already exists. Pleas input new user");
                        user = inputUser();
                    }
                    System.out.println("User: " + user.getName() + " with id: " + user.getId() + " successfully added");
                    break;

                case "3":
                    if (taskService.getAll().size() != 0) {
                        for (Task task2 : taskService.getAll()) {
                            outputTaskInfo(task2, userService.getAll());
                        }
                    } else {
                        System.out.println("Empty task list");
                    }
                    break;

                case "4":
                    if (userService.getAll().size() != 0) {
                        for (User user2 : userService.getAll()) {
                            outputUserInfo(user2, taskService.getAll());
                        }
                    } else {
                        System.out.println("Empty user list");
                    }
                    break;

                case "5":
                    task = inputTask();
                    if (taskService.get(task.getName()) == null) {
                        System.out.println("A task with the same name don't exists");
                    } else {
                        outputTaskInfo(taskService.get(task.getName()), userService.getAll());
                    }
                    break;

                case "6":
                    user = inputUser();
                    if (userService.get(user.getName()) == null) {
                        System.out.println("A user with the same name don't exists");
                    } else {
                        outputUserInfo(userService.get(user.getName()), taskService.getAll());
                    }
                    break;

                case "7":
                    task = inputTask();
                    if (!taskService.delete(task.getName())) {
                        System.out.println("A task with the same name don't exists");
                    } else {
                        System.out.println("Task: " + task.getName() + " successfully deleted");
                    }
                    break;

                case "8":
                    user = inputUser();
                    if (userService.get(user.getName()) == null) {
                        System.out.println("A user with the same name don't exists");
                    } else {
                        userService.delete(user.getName());
                        System.out.println("\nUser: " + user.getName() + " with his tasks, successfully deleted");
                    }
                    break;

                case "9":
                    break;


                default:
                    error();
            }
        }
    }

    public void error() {
        System.out.println("Wrong number, please enter another number");
    }

    public LocalDate inputData() {
        Scanner scanner = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        String yearS, monthS, dayOfMontshS;
        System.out.println("Enter year of deadline");
        yearS = scanner.nextLine();
        while (!errorCheckForNumber(yearS) || (Integer.parseInt(yearS) < date.getYear())) {
            System.out.println("Wrong string or past date, please enter another year");
            yearS = scanner.nextLine();
        }
        int year = Integer.parseInt(yearS);
        System.out.println("Enter month of deadline (1-12)");
        monthS = scanner.nextLine();
        while (!errorCheckForNumber(monthS) || ((year == date.getYear()) && (Integer.parseInt(monthS) < date.getMonthValue()))
                || Integer.parseInt(monthS) < 1 || Integer.parseInt(monthS) > 12) {
            System.out.println("Wrong string or past date, please enter another month");
            monthS = scanner.nextLine();
        }
        int month = Integer.parseInt(monthS);
        System.out.println("Enter month of deadline (1-31)");
        dayOfMontshS = scanner.nextLine();
        while (!errorCheckForNumber(dayOfMontshS) || (year == date.getYear() && month == date.getMonthValue() && Integer.parseInt(dayOfMontshS)
                < date.getDayOfMonth()) || Integer.parseInt(dayOfMontshS) < 1 || Integer.parseInt(dayOfMontshS) > 31) {
            System.out.println("Wrong string or past date, please enter another day");
            dayOfMontshS = scanner.nextLine();
        }
        int dayOfMonth = Integer.parseInt(dayOfMontshS);
        date = LocalDate.of(year, month, dayOfMonth);
        return date;
    }

    public boolean errorCheckForNumber(String date) {
        if (date.chars().allMatch(Character::isDigit) && !date.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Task inputTask() {
        Scanner nameScanner = new Scanner(System.in);
        String taskName;
        System.out.println("Enter name of task: ");
        taskName = nameScanner.nextLine();

        while (taskName.length() == 0) {
            System.out.println("Empty string, please enter new task name:");
            taskName = nameScanner.nextLine();
        }
        taskName = taskName.replace(",", " ");
        return new Task(taskName);
    }

    public User inputUser() {
        Scanner nameScanner = new Scanner(System.in);
        String userName;

        System.out.println("Enter name of User: ");
        userName = nameScanner.nextLine();

        while (userName.length() == 0) {
            System.out.println("Empty string, please enter new user name:");
            userName = nameScanner.nextLine();
        }
        userName = userName.replace(",", " ");
        return new User(userName);
    }

    public void outputTaskInfo(Task task, Collection<User> users) {
        System.out.println("Task id: " + task.getId());
        System.out.println("Name of task: " + task.getName());
        System.out.println("Task deadline: " + task.getDeadline());
        System.out.print("Task user: ");
        for (User user : users) {
            if (user.getId() == task.getUserId()) {
                System.out.println(user.getName() + "\n");
                break;
            }
        }
    }

    public void outputUserInfo(User user, Collection<Task> tasks) {
        System.out.println("User name: " + user.getName());
        System.out.println("User id: " + user.getId());
        System.out.print("User tasks: ");
        for (Task task : tasks) {
            if (user.getId() == task.getUserId()) {
                System.out.print(task.getName() + "   ");
            }
        }
        System.out.println("\n");
    }
}