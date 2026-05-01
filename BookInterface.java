import java.util.*;


public interface BookInterface {


    // From Project Requirements: "A default method that displays the details of all books stored in the book application."
    public default void displayAllBooks() {
        // Checks if the application is empty to prevent a runtime error
        if (Book.getBookRecords().isEmpty()) {
            System.out.println("No books are stored in the application yet.");
        } else {
            for (Book book : Book.getBookRecords()) {
                book.displayDetails();
            }
        }
    }

    // Counts the number of books in each genre
    // From Project Requirements "A method (numberBooksPerGenre()) that returns the number of books in each genre."
    public Map<String, Integer> numberBooksPerGenre();

    // Sums the calculated cost of every book in the application
    // From Project Requirements "A method (getTotalCost()) that returns the total computed cost of all stored books."
    public double getTotalCost();

}
