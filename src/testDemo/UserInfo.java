package testDemo;
import java.util.Scanner;

public class UserInfo {
    
    
    public static boolean isAdult(int age) {
        return age >= 18;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        System.out.println("\nHello, " + name + "!");

        if (isAdult(age)) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are not an adult.");
        }

        sc.close();
    }
}


