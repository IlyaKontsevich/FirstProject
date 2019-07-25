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
        System.out.println("    5.Find task by name");
        System.out.println("    6.Find user by name");
        System.out.println("    7.Delete task");
        System.out.println("    8.Delete user");
        System.out.println("    9.exit");
    }

    public void scan() {
        String symbol = "";
        Scanner symbolScanner = new Scanner(System.in);

        while (!symbol.equals("9")) {
            User user;
            Task task;
            Integer position = 0;
            output();
            symbol = symbolScanner.nextLine();
            switch (symbol) {
                case "1":
                    task = inputTask();
                    user = inputUser();
                    if (existUserCheck(user.getName(), userService.getAll()) == null) {
                        user = userService.add(user);
                        task.setUserId(user.getId());
                        task.setDeadline(inputData());
                        task = taskService.add(task);
                        System.out.println("User: " + user.getName() + " with id: " + user.getId() + " successfully added");
                        System.out.println("Task: " + task.getName() + " with id: " + task.getId() + " successfully added");
                    } else {
                        user = existUserCheck(user.getName(), userService.getAll());
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
                    position = 0;
                    if (taskService.getAll().size() != 0) {
                        while (position < taskService.getAll().size()) {
                            for (Task task2 : taskService.getPage(position)) {
                                outputTaskInfo(task2, userService.get(task2.getUserId()));
                            }
                            System.out.print("number of elements: (" + taskService.getSize() + ");  ");
                            position = chosePosition(position);
                        }
                    } else {
                        System.out.println("Empty task list");
                    }
                    break;

                case "4":
                    position = 0;
                    if (userService.getSize() != 0) {
                        while (position < userService.getAll().size()) {
                            for (User user2 : userService.getPage(position)) {
                                outputUserInfo(user2, taskService.getAllById(user2.getId()));
                            }
                            System.out.print("number of elements: (" + userService.getSize() + ");  ");
                            position = chosePosition(position);
                        }
                    } else {
                        System.out.println("Empty user list");
                    }
                    break;

                case "5":
                    task = inputTask();
                    task = existTaskCheck(task.getName(), taskService.getAll());
                    if (task == null) {
                        System.out.println("A task with the same name don't exists");
                    } else {
                        outputTaskInfo(task, userService.get(task.getUserId()));
                    }
                    break;

                case "6":
                    user = inputUser();
                    user = existUserCheck(user.getName(), userService.getAll());
                    if (user == null) {
                        System.out.println("A user with the same name don't exists");
                    } else {
                        outputUserInfo(user, taskService.getAllById(user.getId()));
                    }
                    break;

                case "7":
                    if (!taskService.delete(inputId())) {
                        System.out.println("A task with the same id don't exists");
                    } else {
                        System.out.println("Task successfully deleted");
                    }
                    break;

                case "8":
                    if (!userService.delete(inputId())) {
                        System.out.println("A user with the same id don't exists");
                    } else {
                        System.out.println("User successfully deleted");
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

    public Integer chosePage(String expression) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(expression);
        String num;
        num = scanner.nextLine();
        while (!num.equals("1") && !num.equals("2")) {
            System.out.println("Wrong ,try again:");
            num = scanner.nextLine();
        }
        return Integer.parseInt(num);
    }

    public Integer chosePosition(Integer position) {
        if (position == 0) {
            while (chosePage("2-next") != 2) {
                System.out.println("Wrong, try again: ");
            }
            position = position + 3;
        } else {
            if (chosePage("1-back, 2-next") == 1) {
                position = position - 3;
            } else {
                position = position + 3;
            }
        }
        return position;
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

    public User existUserCheck(String name, Collection<User> users) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Task existTaskCheck(String name, Collection<Task> tasks) {
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        return null;
    }

    public Integer inputId() {
        Scanner nameScanner = new Scanner(System.in);
        String id;

        System.out.println("Enter id: ");
        id = nameScanner.nextLine();

        while (!errorCheckForNumber(id)) {
            System.out.println("Wrong, please try again:");
            id = nameScanner.nextLine();
        }
        return Integer.parseInt(id);
    }

    public void outputTaskInfo(Task task, User user) {
        System.out.println("\nTask id: " + task.getId());
        System.out.println("Name of task: " + task.getName());
        System.out.println("Task deadline: " + task.getDeadline());
        System.out.println("Task user: " + user.getName());

    }

    public void outputUserInfo(User user, Collection<Task> tasks) {
        System.out.println("    User name: " + user.getName());
        System.out.println("    User id: " + user.getId());
        System.out.print("     User tasks: ");
        for (Task task : tasks) {
            if (user.getId() == task.getUserId()) {
                System.out.print(task.getName() + "   ");
            }
        }
        System.out.println("\n");
    }
}