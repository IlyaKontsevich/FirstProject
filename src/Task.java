import java.util.Scanner;

public class Task {
    private String id;
    private String name;
    private User user;
    private String deadline;


    public Task() {
        Scanner scaner = new Scanner(System.in);

        System.out.println("Enter task id: ");
        id = scaner.nextLine();
        System.out.println("Enter task name: ");
        name = scaner.nextLine();
        System.out.println("Enter task deadline: ");
        deadline = scaner.nextLine();
        this.user = new User();
        System.out.println("Task successfully add");
    }
    public User GetUser(){
        return user;
    }

    public void GetInfo(){
        System.out.println("Task id: " + id);
        System.out.println("Task name: " + name);
        System.out.println("Task deadline: " + deadline);
        System.out.println("User name: " + user.GetName());
    }

}
