import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Static Scanner like the examples from class, so the helper methods can use it too.
    private static Scanner scnr = new Scanner(System.in);
    private static BookApplication app = new BookApplication();

    public static void main(String[] args) {
        BookApplication books = app;
        boolean flag = true;

        printActions();

        while (flag) {
            int option = readInt("", 0, 12);

            switch (option) {
                case 0 -> addPrintedBook(books);
                case 1 -> addAudioBook(books);
                case 2 -> books.displayAllBooks();
                case 3 -> searchBooks(books);
                case 4 -> sortBooks(books);
                case 5 -> books.displayStatistics();
                case 6 -> books.displayLastSixBooks();
                case 7 -> books.displayLastThreePrintedBooks();
                case 8 -> books.displayLastThreeAudioBooks();
                case 9 -> saveBooks(books);
                case 10 -> loadBooks(books);
                case 11 -> {
                    books.clearBooks();
                    System.out.println("All books have been cleared.");
                }
                case 12 -> flag = false;
                default -> System.out.println("Invalid option! Try again.");
            }

            // Ask the user if they want to continue, like the class examples.
            if (flag) {
                System.out.print("do you want to continue (Y/N): ");
                String answer = scnr.nextLine();

                if (answer.length() > 0 && (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y')) {
                    printActions();
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }

        System.out.println("---thank you program shutting---!");
    }

    private static void printActions() {
        // Text block menu like the grocery list example from class.
        String textBlock = """
                ==================================
                    BOOK APPLICATION MENU
                ==================================
                0  - add printed book
                1  - add audiobook
                2  - display all books
                3  - search books
                4  - sort books
                5  - display statistics
                6  - display last 6 books
                7  - display last 3 printed books
                8  - display last 3 audiobooks
                9  - save books to file
                10 - load books from file
                11 - clear all books
                12 - exit
                ----------------------------------
                Enter a number for which action you want to do: """;

        System.out.print(textBlock + " ");
    }

    private static void addPrintedBook(BookApplication books) {
        System.out.println("--- Add Printed Book ---");

        String title = readLine("Enter title: ");
        String author = readLine("Enter author: ");
        String genre = readLine("Enter genre: ");
        int pages = readInt("Enter number of pages: ", 0, Integer.MAX_VALUE);

        books.addBook(new PrintedBook(title, author, genre, pages));
        System.out.println("Printed book added.");
    }

    private static void addAudioBook(BookApplication books) {
        System.out.println("--- Add Audiobook ---");

        String title = readLine("Enter title: ");
        String author = readLine("Enter author: ");
        String genre = readLine("Enter genre: ");
        int minutes = readInt("Enter duration in minutes: ", 0, Integer.MAX_VALUE);

        books.addBook(new AudioBook(title, author, genre, minutes));
        System.out.println("Audiobook added.");
    }

    private static void searchBooks(BookApplication books) {
        String searchMenu = """
                ---------- SEARCH MENU ----------
                1 - search by title
                2 - search by author
                3 - search by genre
                4 - search by book type
                Enter your search option: """;

        System.out.print(searchMenu + " ");

        int option = readInt("", 1, 4);
        ArrayList<Book> results = new ArrayList<>();

        switch (option) {
            case 1 -> {
                String title = readLine("Enter title or part of title: ");
                results = books.searchByTitle(title);
            }
            case 2 -> {
                String author = readLine("Enter author or part of author name: ");
                results = books.searchByAuthor(author);
            }
            case 3 -> {
                String genre = readLine("Enter genre: ");
                results = books.searchByGenre(genre);
            }
            case 4 -> {
                String type = readLine("Enter book type, PrintedBook or AudioBook: ");
                results = books.searchByBookType(type);
            }
            default -> System.out.println("Invalid search option.");
        }

        books.displayBooks(results);
    }

    private static void sortBooks(BookApplication books) {
        String sortMenu = """
                ---------- SORT MENU ----------
                1 - sort by title
                2 - sort by author
                3 - sort by genre
                4 - sort by cost
                Enter your sort option: """;

        System.out.print(sortMenu + " ");

        int option = readInt("", 1, 4);

        switch (option) {
            case 1 -> books.sortByTitle();
            case 2 -> books.sortByAuthor();
            case 3 -> books.sortByGenre();
            case 4 -> books.sortByCost();
            default -> System.out.println("Invalid sort option.");
        }
    }

    private static void saveBooks(BookApplication books) {
        String fileName = readLine("Enter file name to save to, for example saved_books.txt: ");
        books.saveToFile(fileName);
    }

    private static void loadBooks(BookApplication books) {
        String fileName = readLine("Enter file name to load, for example sample TXT Project file.txt: ");
        books.loadFromFile(fileName);
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scnr.nextLine();
    }

    private static int readInt(String prompt, int min, int max) {
        int number = 0;
        boolean valid = false;

        while (!valid) {
            if (prompt.length() > 0) {
                System.out.print(prompt);
            }

            try {
                String input = scnr.nextLine();
                number = Integer.parseInt(input);

                if (number < min || number > max) {
                    System.out.println("Invalid number. Please enter a number from " + min + " to " + max + ".");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer with no letters or spaces.");
            }
        }

        return number;
    }
}