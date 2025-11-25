package myCollections;

import java.util.Scanner;

public class LibraryDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        
        library.addBook(new Book("B1", "Java Basics", "James Gosling", 150));
        library.addBook(new Book("B2", "Advanced Java", "Herbert Schildt", 450));
        library.addBook(new Book("B3", "Python for All", "Guido van Rossum", 180));
        library.addBook(new Book("B4", "C++ Mastery", "Bjarne Stroustrup", 400));


        System.out.print("Enter your name: ");
        User user = new User(sc.nextLine());

        int choice;
        do {
            System.out.println("===== LIBRARY MENU =====");
            System.out.println("1. Show All Books");
            System.out.println("2. Search Book by Title");
            System.out.println("3. Show Books by Price Range");
            System.out.println("4. Buy Book");
            System.out.println("5. Add New Book (Admin)");
            System.out.println("6. Remove Book (Admin)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> library.showAllBooks();
                case 2 -> {
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    library.searchBookByTitle(title);
                }
                case 3 -> {
                    System.out.print("Enter min price: ");
                    double min = sc.nextDouble();
                    System.out.print("Enter max price: ");
                    double max = sc.nextDouble();
                    library.showBooksByPriceRange(min, max);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to buy: ");
                    String id = sc.next();
                    user.buyBook(library, id);
                }
                case 5 -> {
                    System.out.print("Enter new Book ID: ");
                    String id = sc.next();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    library.addBook(new Book(id, title, author, price));
                }
                case 6 -> {
                    System.out.print("Enter Book ID to remove: ");
                    String id = sc.next();
                    library.removeBook(id);
                }
                case 7 -> System.out.println("Thank you for visiting!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
