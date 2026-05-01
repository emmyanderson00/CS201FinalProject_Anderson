import java.util.ArrayList;

public class AudioBook extends Book {
    private int durationMinutes;

    // These are static because they describe the group of all audiobooks added.
    private static ArrayList<AudioBook> lastThreeAudio = new ArrayList<>();
    private static int audioCount = 0;
    private static int totalAudioMinutes = 0;

    // Inheritance: AudioBook is a Book, so it calls the Book constructor first.
    public AudioBook(String title, String author, String genre, int durationMinutes) {
        super(title, author, genre, "AudioBook");
        setDurationMinutes(durationMinutes);
    }

    // This extra constructor lets older test code still compile if it passes a cost value.
    // The cost is still computed from minutes because each minute costs $0.25.
    public AudioBook(String title, String author, String genre, double cost, int durationMinutes) {
        this(title, author, genre, durationMinutes);
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes < 0) {
            this.durationMinutes = 0;
        } else {
            this.durationMinutes = durationMinutes;
        }
    }

    // Overriding: AudioBook uses a different cost formula than PrintedBook.
    @Override
    public double getCost() {
        return durationMinutes * 0.25;
    }

    // This is called by BookApplication only after the audiobook is actually added.
    public void addToLastThreeAudio() {
        audioCount++;
        totalAudioMinutes += durationMinutes;

        if (lastThreeAudio.size() == 3) {
            lastThreeAudio.remove(0);
        }
        lastThreeAudio.add(this);
    }

    public static double getAveLength() {
        if (audioCount == 0) {
            return 0;
        }
        return (double) totalAudioMinutes / audioCount;
    }

    // Same idea as getAveLength(), but this name is easier to understand in the video.
    public static double getAverageDuration() {
        return getAveLength();
    }

    public static void displayLast3AudioBook() {
        if (lastThreeAudio.isEmpty()) {
            System.out.println("No audiobooks have been added yet.");
        } else {
            for (AudioBook book : lastThreeAudio) {
                book.displayDetails();
            }
        }
    }

    public static void clearAudioRecords() {
        lastThreeAudio.clear();
        audioCount = 0;
        totalAudioMinutes = 0;
    }

    public static int getAudioCount() {
        return audioCount;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Duration Minutes: " + durationMinutes);
        System.out.println("------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + " | Minutes: " + durationMinutes;
    }
}