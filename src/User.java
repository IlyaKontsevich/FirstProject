import java.util.Scanner;

public class User {
    private String name;


    public String GetName(){
        return name;
    }

    public User(){
        Scanner scaner = new Scanner(System.in);

        System.out.println("Enter user name: ");
        name = scaner.nextLine();
        System.out.println("User successfully add");
    }

}
