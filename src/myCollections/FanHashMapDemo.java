package myCollections;
import java.util.*;

public class FanHashMapDemo {

    private static HashMap<String, FanDetails> fanMap = new HashMap<>();

    public static void main(String[] args) {
        setupDummyData();
        menu();
    }

    public static void setupDummyData() {
        fanMap.put("Akash", new FanDetails("Akash", "India", "Uttarakhand", "248002", "9876543210"));
        fanMap.put("Riya", new FanDetails("Riya", "India", "Delhi", "110001", "8765432109"));
        fanMap.put("Karan", new FanDetails("Karan", "India", "Maharashtra", "400001", "7654321098"));
        fanMap.put("Karan", new FanDetails("James", "USA", "California", "USA4001", "81024562"));
        fanMap.put("Karan", new FanDetails("Karan", "Nepal", "Kathmandu", "NAP5451", "7654321098"));
        System.out.println("Dummy data loaded successfully!");
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Fan Management Menu =====");
            System.out.println("1. Search Fan by Name");
            System.out.println("2. Display All Fans");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name to search: ");
                    String name = sc.nextLine();
                    FanDetails fan = fanMap.get(name);
                    if (fan != null)
                        System.out.println(fan);
                    else
                        System.out.println("Fan not found!");
                    break;

                case 2:
                    System.out.println("--- All Fans ---");
                    for (FanDetails f : fanMap.values())
                        System.out.println(f);
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}
