import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class BookApplication implements BookInterface {
    // Encapsulation: the ArrayLists are private so Main cannot directly change them.
    private ArrayList<Book> books;
    private ArrayList<Book> lastSixBooks;

    public BookApplication() {
        books = new ArrayList<>();
        lastSixBooks = new ArrayList<>();

        // This keeps a new run/test from accidentally using old static records.
        Book.clearBookRecords();
        PrintedBook.clearPrintedRecords();
        AudioBook.clearAudioRecords();
    }

    public void addBook(Book book) {
        books.add(book);
        Book.addBookRecord(book);

        // Last N logic: keep only the last 6 books overall.
        if (lastSixBooks.size() == 6) {
            lastSixBooks.remove(0);
        }
        lastSixBooks.add(book);

        // Polymorphism/type checking: BookApplication stores Book objects,
        // but here I check the real subclass to update the correct last 3 list.
        if (book instanceof PrintedBook) {
            PrintedBook printedBook = (PrintedBook) book;
            printedBook.addToLastThreePrinted();
        } else if (book instanceof AudioBook) {
            AudioBook audioBook = (AudioBook) book;
            audioBook.addToLastThreeAudio();
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void clearBooks() {
        books.clear();
        lastSixBooks.clear();
        Book.clearBookRecords();
        PrintedBook.clearPrintedRecords();
        AudioBook.clearAudioRecords();
    }

    public void addBooksFromList(ArrayList<Book> loadedBooks) {
        clearBooks();

        for (Book book : loadedBooks) {
            addBook(book);
        }
    }

    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    public ArrayList<Book> searchByGenre(String genre) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }

        return result;
    }

    public ArrayList<Book> searchByBookType(String bookType) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getBookType().equalsIgnoreCase(bookType)) {
                result.add(book);
            }
        }

        return result;
    }

    public void sortByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
        displayAllBooks();
    }

    public void sortByAuthor() {
        books.sort(Comparator.comparing(Book::getAuthor));
        displayAllBooks();
    }

    public void sortByGenre() {
        books.sort(Comparator.comparing(Book::getGenre));
        displayAllBooks();
    }

    public void sortByCost() {
        books.sort(Comparator.comparing(Book::getCost));
        displayAllBooks();
    }

    @Override
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books are stored in the application yet.");
        } else {
            for (Book book : books) {
                book.displayDetails();
            }
        }
    }

    public void displayBooks(ArrayList<Book> list) {
        if (list.isEmpty()) {
            System.out.println("No matching books were found.");
        } else {
            for (Book book : list) {
                book.displayDetails();
            }
        }
    }

    public void displayLastSixBooks() {
        if (lastSixBooks.isEmpty()) {
            System.out.println("No books have been added yet.");
        } else {
            for (Book book : lastSixBooks) {
                book.displayDetails();
            }
        }
    }

    public void displayLastThreePrintedBooks() {
        PrintedBook.displayLast3PrintedBook();
    }

    public void displayLastThreeAudioBooks() {
        AudioBook.displayLast3AudioBook();
    }

    @Override
    public Map<String, Integer> numberBooksPerGenre() {
        // I call the Book version because the abstract class implements this required method.
        if (books.isEmpty()) {
            return new java.util.HashMap<>();
        }
        return books.get(0).numberBooksPerGenre();
    }

    @Override
    public double getTotalCost() {
        double total = 0;

        for (Book book : books) {
            total += book.getCost();
        }

        return total;
    }

    public void displayStatistics() {
        DecimalFormat money = new DecimalFormat("$#,##0.00");
        DecimalFormat number = new DecimalFormat("#,##0.00");

        System.out.println("========== BOOK STATISTICS ==========");
        System.out.println("Total books stored: " + books.size());
        System.out.println("Printed books stored: " + PrintedBook.getPrintedCount());
        System.out.println("Audiobooks stored: " + AudioBook.getAudioCount());
        System.out.println("Total computed cost: " + money.format(getTotalCost()));
        System.out.println("Average pages for printed books: " + number.format(PrintedBook.getAvePages()));
        System.out.println("Average duration for audiobooks: " + number.format(AudioBook.getAveLength()) + " minutes");

        System.out.println("\nBooks per genre:");
        Map<String, Integer> genreMap = numberBooksPerGenre();
        if (genreMap.isEmpty()) {
            System.out.println("No genre data available yet.");
        } else {
            for (String genre : genreMap.keySet()) {
                System.out.println(genre + ": " + genreMap.get(genre));
            }
        }
        System.out.println("=====================================");
    }

    public void saveToFile(String fileName) {
        try {
            FileManager.saveBooks(books, fileName);
            System.out.println("Books saved to " + fileName);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try {
            ArrayList<Book> loadedBooks = FileManager.loadBooks(fileName);
            addBooksFromList(loadedBooks);
            System.out.println(books.size() + " books loaded from " + fileName);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}