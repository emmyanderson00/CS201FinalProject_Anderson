public class TestCase {
    public static void main(String[] args) {
        BookApplication app = new BookApplication();

        System.out.println("--- Loading Sample Data File ---");
        app.loadFromFile("sample TXT Project file.txt");

        System.out.println("\n--- Adding Required Edge Case and Large Value Books ---");
        app.addBook(new PrintedBook("Empty Printed Book", "Edge Tester", "Testing", 0));
        app.addBook(new AudioBook("Silent Audio", "Edge Tester", "Testing", 0));
        app.addBook(new PrintedBook("Massive Printed Book", "Big Author", "Testing", 1000));
        app.addBook(new AudioBook("Marathon Audio", "Big Author", "Testing", 2000));

        System.out.println("\n--- Displaying All Books ---");
        app.displayAllBooks();

        System.out.println("\n--- Last 6 Overall Books ---");
        app.displayLastSixBooks();

        System.out.println("\n--- Last 3 Printed Books ---");
        app.displayLastThreePrintedBooks();

        System.out.println("\n--- Last 3 Audiobooks ---");
        app.displayLastThreeAudioBooks();

        System.out.println("\n--- Statistics and Genre Counting ---");
        app.displayStatistics();

        System.out.println("\n--- Search by Genre: Testing ---");
        app.displayBooks(app.searchByGenre("Testing"));

        System.out.println("\n--- Search by Author: Big ---");
        app.displayBooks(app.searchByAuthor("Big"));

        System.out.println("\n--- Saving Books to File ---");
        app.saveToFile("saved_books.txt");

        System.out.println("\n--- Clearing and Reloading Saved File ---");
        app.clearBooks();
        app.loadFromFile("saved_books.txt");

        System.out.println("\n--- Displaying Statistics After Reload ---");
        app.displayStatistics();
    }
}