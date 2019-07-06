import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] Args){
        Scanner scaner = new Scanner(System.in);
        image();
        scan();
        System.out.println("EXIT");
    }
    static void image() {
        System.out.println("            Console application");
        System.out.println("1.Say Hello");
        System.out.println("2.another metod");
        System.out.println("3.exit");
    }

    static void scan(){
        int sym = 0;
        Scanner scaner = new Scanner(System.in);

        while (sym != 3) {
            sym = scaner.nextInt();
            switch(sym)
            {
                case 1:
                    Hello();
                    break;

                case 2:
                    OtherMetod();
                    break;

                case 3:
                    break;
                default:
                Error();
            }
        }
    }
    static void Hello(){
        System.out.println("Hello");
    }
    static void OtherMetod(){
        System.out.println("Other Metod");
    }
    static void Error(){
        System.out.println("Error.");
    }
}
