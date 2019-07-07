import java.util.*;

public class ConsoleApp {
    private List<task> tasks = new ArrayList();
    private List<User> users = new ArrayList();

    public static void main(String[] Args){
        ConsoleApp apllication = new ConsoleApp();

        apllication.scan();
        System.out.println("EXIT");
    }


     public void image() {
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
            image();
            symbol = scaner.nextInt();
            switch(symbol)
            {
                case 1:
                    AddTask();
                    break;

                case 2:
                    AddUser();
                    break;

                case 3:
                    ShowTask();
                    break;

                case 4:
                    ShowUser();
                    break;

                case 5:
                    break;


                default:
                Error();
            }
        }
    }

    public void AddTask(){
        tasks.add(new task());
        users.add(tasks.get(tasks.size() - 1).GetUser());
    }


    public void AddUser(){
        users.add(new User());
    }

    public void ShowUser(){
        for (User user : users){
            System.out.println(user.GetName());
        }
    }

    public void ShowTask(){
        for (task Task : tasks){
            Task.GetInfo();
            System.out.println();
        }
    }

    public void Error(){
        System.out.println("Error.");
    }
}
