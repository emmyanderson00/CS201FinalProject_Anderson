# CS201FinalProject_Anderson
CS 201 Final Project Book Application

## Project Description

This project is a command-line Book Application system for CS 201. The purpose of the program is to store and keep track of books that a user has read. The program works with two types of books: printed books and audiobooks.

For each book, the program stores information such as the title, author, genre, and book type. Printed books also store the number of pages, and audiobooks store the duration in minutes. The program also calculates the cost of each book based on the project requirements.

Printed books cost `$0.50` per page.  
Audiobooks cost `$0.25` per minute.

The program allows the user to add books, search for books, sort books, display statistics, save book data to a file, and load book data from a file.

---

## How to Run the Program

Make sure all of the `.java` files and the sample text file are in the same project folder.

The main files are:

- `Main.java`
- `BookInterface.java`
- `Book.java`
- `PrintedBook.java`
- `AudioBook.java`
- `BookApplication.java`
- `FileManager.java`
- `TestCase.java`
- `sample TXT Project file.txt`

To compile the program, run:

```bash
javac *.java
```

To run the command-line application, run:

```bash
java Main
```

To run the test case file, run:

```bash
java TestCase
```

---

## Program Features

The command-line menu allows the user to:

- Add a printed book
- Add an audiobook
- Display all books
- Search books
- Sort books
- Display statistics
- Display the last 6 books added
- Display the last 3 printed books added
- Display the last 3 audiobooks added
- Save books to a file
- Load books from a file
- Clear all books
- Exit the program

The program asks the user if they want to continue after each menu option.

---

## File Loading and Saving

The program can load book data from a text file. I used the provided sample project file:

```text
sample TXT Project file.txt
```

The program can also save the current list of books to a new text file. For example:

```text
saved_books.txt
```

This lets the user close the program and reload the saved data later.

---

## Design Explanation

The project uses an interface, an abstract class, inheritance, and separate classes for different responsibilities.

### BookInterface

`BookInterface` contains the required methods for the book application. It includes methods for displaying all books, counting the number of books per genre, and calculating the total cost of all books.

### Book

`Book` is an abstract class. I made it abstract because a general book has shared information like title, author, genre, and book type, but the cost calculation depends on the type of book.

The `Book` class stores the common book fields and has an abstract `getCost()` method. This means each subclass must provide its own cost formula.

### PrintedBook

`PrintedBook` extends `Book`. It stores the number of pages in a printed book. The cost is calculated by multiplying the number of pages by `0.50`.

It also keeps track of the last 3 printed books added and calculates the average number of pages for all printed books.

### AudioBook

`AudioBook` extends `Book`. It stores the duration of the audiobook in minutes. The cost is calculated by multiplying the number of minutes by `0.25`.

It also keeps track of the last 3 audiobooks added and calculates the average audiobook duration.

### BookApplication

`BookApplication` manages the main list of books. It handles adding books, searching, sorting, displaying statistics, displaying last-N book lists, saving, and loading.

### FileManager

`FileManager` handles file input and output. It reads book data from a text file and writes book data to a text file.

### Main

`Main` contains the command-line interface. It displays the menu and gets user input.

### TestCase

`TestCase` is a separate testing file. It loads the professor’s sample file, adds the required edge case and large value books, and demonstrates the main project requirements.

---

## OOP Concepts Used

### Encapsulation

Encapsulation is used by making the important fields private. For example, fields like `title`, `author`, `genre`, `pages`, and `durationMinutes` are private. Other classes use getter and setter methods to access or update those values.

### Inheritance

Inheritance is used because `PrintedBook` and `AudioBook` both extend the abstract `Book` class. This allows both subclasses to reuse the common fields and methods from `Book`.

### Polymorphism

Polymorphism is used because the program stores both printed books and audiobooks in one `ArrayList<Book>`. When the program calls `getCost()`, Java uses the correct version of the method depending on whether the object is a `PrintedBook` or an `AudioBook`.

### Abstraction

Abstraction is used with the abstract `Book` class and the abstract `getCost()` method. The general `Book` class does not calculate cost directly because the formula is different for printed books and audiobooks.

---

## Test Cases Demonstrated

The project demonstrates the required test cases, including:

- Adding more than 8 books
- Displaying the last 6 books added overall
- Displaying the last 3 printed books added
- Displaying the last 3 audiobooks added
- A printed book with 0 pages and cost `$0.00`
- An audiobook with 0 minutes and cost `$0.00`
- A 1000-page printed book
- A 2000-minute audiobook
- Genre counting
- Searching by genre
- Searching by author
- Saving books to a file
- Clearing the program data
- Reloading books from a saved file

---

## Notes

I used a command-line interface because it fits the style of the programs we made during the course. The menu is simple and lets the user choose an action by entering a number.

The program also includes comments in the code to explain where the main object-oriented programming concepts are used.
