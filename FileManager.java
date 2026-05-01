import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String HEADER = "Title,Author,Genre,BookType,Pages,Duration_Minutes,Cost";

    public static void saveBooks(ArrayList<Book> books, String fileName) throws IOException {
        PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));
        outputFile.println(HEADER);

        for (Book book : books) {
            outputFile.println(makeFileLine(book));
        }

        outputFile.close();
    }

    public static ArrayList<Book> loadBooks(String fileName) throws IOException {
        ArrayList<Book> loadedBooks = new ArrayList<>();
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);
        int lineNumber = 0;

        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();
            lineNumber++;

            if (line.trim().length() > 0) {
                String[] data = line.split(",");

                // The first line in the sample file is a header, so this skips it.
                if (lineNumber == 1 && data[0].equalsIgnoreCase("Title")) {
                    // Do nothing for the header line.
                } else if (data.length >= 6) {
                    String title = data[0].trim();
                    String author = data[1].trim();
                    String genre = data[2].trim();
                    String bookType = data[3].trim();
                    String pagesText = data[4].trim();
                    String minutesText = data[5].trim();

                    if (bookType.equalsIgnoreCase("Printed") || bookType.equalsIgnoreCase("PrintedBook")) {
                        int pages = parseNumber(pagesText);
                        loadedBooks.add(new PrintedBook(title, author, genre, pages));
                    } else if (bookType.equalsIgnoreCase("Audio") || bookType.equalsIgnoreCase("AudioBook")) {
                        int minutes = parseNumber(minutesText);
                        loadedBooks.add(new AudioBook(title, author, genre, minutes));
                    }
                }
            }
        }

        inputFile.close();
        return loadedBooks;
    }

    private static String makeFileLine(Book book) {
        String pages = "null";
        String minutes = "null";
        String type = book.getBookType();

        if (book instanceof PrintedBook) {
            PrintedBook printedBook = (PrintedBook) book;
            pages = Integer.toString(printedBook.getPages());
            type = "Printed";
        } else if (book instanceof AudioBook) {
            AudioBook audioBook = (AudioBook) book;
            minutes = Integer.toString(audioBook.getDurationMinutes());
            type = "Audio";
        }

        return book.getTitle() + "," +
                book.getAuthor() + "," +
                book.getGenre() + "," +
                type + "," +
                pages + "," +
                minutes + "," +
                String.format("%.2f", book.getCost());
    }

    private static int parseNumber(String text) {
        if (text == null || text.equalsIgnoreCase("null") || text.length() == 0) {
            return 0;
        }

        try {
            int number = Integer.parseInt(text);
            if (number < 0) {
                return 0;
            }
            return number;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}