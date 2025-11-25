package myCollections;
import java.util.*;

public class HashMapDictionaryDemo {

      private static HashMap<String, String> dictionary = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setupDummyData();
        menu();
    }
    public static void setupDummyData() {
        dictionary.put("Java", "A high-level programming language.");
        dictionary.put("Map", "A collection that stores key-value pairs.");
        dictionary.put("HashMap", "A data structure that stores data in key-value form.");
        dictionary.put("OOP", "Object Oriented Programming paradigm.");
        dictionary.put("Class", "A blueprint for creating objects.");
        System.out.println(" Preloaded words added to dictionary");
    }


    public static void menu() {
        int choice;
        do {
            System.out.println("===== Dictionary Menu =====");
            System.out.println("1. Add Word");
            System.out.println("2. Search Word");
            System.out.println("3. Display All Words");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addWord();
                    break;
                case 2:
                    searchWord();
                    break;
                case 3:
                    displayAll();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void addWord() {
        System.out.print("Enter word: ");
        String word = sc.nextLine();
        System.out.print("Enter meaning: ");
        String meaning = sc.nextLine();
        dictionary.put(word, meaning);
        System.out.println(" Word added successfully!");
    }

    public static void searchWord() {
        System.out.print("Enter word to search: ");
        String word = sc.nextLine();
        String meaning = dictionary.get(word);
        if (meaning != null)
            System.out.println(word + " means: " + meaning);
        else
            System.out.println(" Word not found!");
    }

    public static void displayAll() {
        System.out.println(" Dictionary Words");
        for (String key : dictionary.keySet()) { 
            System.out.println(key + " : " + dictionary.get(key));
        }
    }
}
