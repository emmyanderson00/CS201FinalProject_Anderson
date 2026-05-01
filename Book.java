import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Book implements BookInterface {
    // Encapsulation: outside classes cannot directly change these private fields
    private String title;
    private String author;
    private String genre;
    private String bookType;

    // This keeps track of all books added so the abstract class can do total cost and genre count
    private static ArrayList<Book> bookRecords = new ArrayList<>();

    public Book(String title, String author, String genre, String bookType) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookType = bookType;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getBookType() {
        return bookType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    // Called whenever a book is added to the application
    public static void addBookRecord(Book book) {
        bookRecords.add(book);
    }

    public static ArrayList<Book> getBookRecords() {
        return bookRecords;
    }

    public static void clearBookRecords() {
        bookRecords.clear();
    }

    // This is in the abstract class because all books share this logic
    @Override
    public Map<String, Integer> numberBooksPerGenre() {
        Map<String, Integer> genreMap = new HashMap<>();

        for (Book book : bookRecords) {
            String currentGenre = book.getGenre();

            if (genreMap.containsKey(currentGenre)) {
                genreMap.put(currentGenre, genreMap.get(currentGenre) + 1);
            } else {
                genreMap.put(currentGenre, 1);
            }
        }

        return genreMap;
    }

    // This is in the abstract class because all books share this logic
    @Override
    public double getTotalCost() {
        double totalCost = 0;

        // Polymorphism: getCost() runs differently based on the type of book
        for (Book book : bookRecords) {
            totalCost += book.getCost();
        }

        return totalCost;
    }

    // Abstraction: every book has a cost, but the formula depends on the subclass
    public abstract double getCost();

    public void displayDetails() {
        DecimalFormat formatter = new DecimalFormat("$#,##0.00");

        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Book Type: " + bookType);
        System.out.println("Computed Cost: " + formatter.format(getCost()));
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("$#,##0.00");
        return title + " by " + author + " | " + genre + " | " + bookType + " | " + formatter.format(getCost());
    }
}