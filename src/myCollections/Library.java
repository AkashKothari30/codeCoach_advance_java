package myCollections;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private HashMap<String, Book> bookMap = new HashMap<>();


    public void addBook(Book book) {
        if(bookMap.containsKey(book.getId())){
                System.out.println("Book ID already exists! Try different ID");
        } else {
            bookMap.put(book.getId(), book);
            System.out.println("Book Added SuccssesFully");
        }
    }

    public void showAllBooks() {
        if (bookMap.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : bookMap.values()) {
            System.out.println(book);
        }
    }

    
    public void searchBookByTitle(String title) {
        boolean found = false;
        for (Book book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + book);
                found = true;
            }
        }
        if (!found) System.out.println("Book not found!");
    }


    public void showBooksByPriceRange(double min, double max) {
        boolean found = false;
        for (Book book : bookMap.values()) {
            if (book.getPrice() >= min && book.getPrice() <= max) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) System.out.println("No books found in this price range!");
    }

    
    public Book getBookById(String id) {
        return bookMap.get(id);
    }

    
    public void removeBook(String id) {
        if (bookMap.remove(id) != null)
            System.out.println("Book removed successfully.");
        else
            System.out.println("Book not found!");
    }
}
