import java.util.ArrayList;

public class PrintedBook extends Book {
    private int pages;

    // These are static because they belong to all PrintedBook objects together
    private static ArrayList<PrintedBook> lastThreePrinted = new ArrayList<>();
    private static int printedCount = 0;
    private static int totalPrintedPages = 0;

    // Inheritance: PrintedBook is a Book, so it calls the superclass constructor using super
    public PrintedBook(String title, String author, String genre, int pages) {
        super(title, author, genre, "PrintedBook");
        setPages(pages);
    }

    // This extra constructor lets older test code still compile if it passes a cost value
    // The cost is still computed from pages because the project says one page costs $0.50
    public PrintedBook(String title, String author, String genre, double cost, int pages) {
        this(title, author, genre, pages);
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages < 0) {
            this.pages = 0;
        } else {
            this.pages = pages;
        }
    }

    // Overriding: PrintedBook gives its own version of getCost().
    @Override
    public double getCost() {
        return pages * 0.50;
    }

    // This is called by BookApplication only after the book is actually added.
    public void addToLastThreePrinted() {
        printedCount++;
        totalPrintedPages += pages;

        if (lastThreePrinted.size() == 3) {
            lastThreePrinted.remove(0);
        }
        lastThreePrinted.add(this);
    }

    public static double getAvePages() {
        if (printedCount == 0) {
            return 0;
        }
        return (double) totalPrintedPages / printedCount;
    }

    // Same idea as getAvePages(), but this name is easier to understand in the video.
    public static double getAveragePages() {
        return getAvePages();
    }

    public static void displayLast3PrintedBook() {
        if (lastThreePrinted.isEmpty()) {
            System.out.println("No printed books have been added yet.");
        } else {
            for (PrintedBook book : lastThreePrinted) {
                book.displayDetails();
            }
        }
    }

    public static void clearPrintedRecords() {
        lastThreePrinted.clear();
        printedCount = 0;
        totalPrintedPages = 0;
    }

    public static int getPrintedCount() {
        return printedCount;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Pages: " + pages);
        System.out.println("------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + " | Pages: " + pages;
    }
}