package myCollections;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void buyBook(Library library, String bookId) {
        Book book = library.getBookById(bookId);
        if (book != null) {
            System.out.println(name + " bought " + book.getTitle() + " for ₹" + book.getPrice());
        } else {
            System.out.println("Invalid book ID!");
        }
    }
}
