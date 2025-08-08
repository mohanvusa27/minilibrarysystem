import java.util.*;

// Book class
class Book {
    private int id;
    private String title;
    private boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { isIssued = issued; }

    public String toString() {
        return "[" + id + "] " + title + (isIssued ? " (Issued)" : " (Available)");
    }
}

// User class
class User {
    private int id;
    private String name;
    private List<Book> issuedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void issueBook(Book book) {
        if (!book.isIssued()) {
            book.setIssued(true);
            issuedBooks.add(book);
            System.out.println(name + " issued book: " + book.getTitle());
        } else {
            System.out.println("Book already issued.");
        }
    }

    public void returnBook(Book book) {
        if (issuedBooks.contains(book)) {
            book.setIssued(false);
            issuedBooks.remove(book);
            System.out.println(name + " returned book: " + book.getTitle());
        } else {
            System.out.println("Book not found in user's issued list.");
        }
    }

    public void showIssuedBooks() {
        System.out.println(name + "'s Issued Books:");
        if (issuedBooks.isEmpty()) {
            System.out.println("  No books issued.");
        } else {
            for (Book book : issuedBooks) {
                System.out.println("  - " + book);
            }
        }
    }
}

// Library class
class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();

    public void addBook(int id, String title) {
        books.put(id, new Book(id, title));
    }

    public void addUser(int id, String name) {
        users.put(id, new User(id, name));
    }

    public void issueBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);
        if (user != null && book != null) {
            user.issueBook(book);
        } else {
            System.out.println("Invalid user or book ID.");
        }
    }

    public void returnBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);
        if (user != null && book != null) {
            user.returnBook(book);
        } else {
            System.out.println("Invalid user or book ID.");
        }
    }

    public void listAllBooks() {
        System.out.println("All Books in Library:");
        for (Book book : books.values()) {
            System.out.println("  - " + book);
        }
    }

    public void showUserBooks(int userId) {
        User user = users.get(userId);
        if (user != null) {
            user.showIssuedBooks();
        } else {
            System.out.println("User not found.");
        }
    }
}

// Main class
public class MiniLibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Sample data
        library.addBook(1, "Java Programming");
        library.addBook(2, "Data Structures");
        library.addBook(3, "Operating Systems");

        library.addUser(101, "Alice");
        library.addUser(102, "Bob");

        // Issue & Return Operations
        library.issueBook(101, 1); // Alice issues Java Programming
        library.issueBook(102, 2); // Bob issues Data Structures

        library.returnBook(101, 1); // Alice returns Java Programming

        // Show status
        library.showUserBooks(101);
        library.showUserBooks(102);
        library.listAllBooks();
    }
}
