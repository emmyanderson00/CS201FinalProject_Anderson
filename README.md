## CS 201 Final Project: Book Application
### Project Description

This is a Book Application system that stores and keeps track of books a user has read. The program runs in the command line and gives the user a menu of options to choose from.
It works with printed books and audiobooks.
For every book, the program stores the title, author, genre, and book type. Printed books also store the number of pages. Audiobooks store the duration in minutes.
The program can also calculate the cost of each book based on the project requirements.
Printed books cost 0.50 per page. Audiobooks cost 0.25 per minute. The program also allows the user to add books, search books, sort books, display statistics, display last-N book records, save book data to a file, and load book data from a file.

### How to Run the Program

All Java files should be in the same project folder.

To compile the program, use:

javac *.java

To run the main command-line program, use:

java Main

I also included a separate test file. To run the test case file, use:

java TestCase

The test case file loads the provided sample text file, adds the extra required edge case and large value books, and displays the required project outputs.

### Files Included

**The project includes these files:**

BookInterface.java  
Book.java   
PrintedBook.java    
AudioBook.java  
BookApplication.java    
FileManager.java    
Main.java   
TestCase.java   
sample TXT Project file.txt 
README.md   
TEST_CASES.md   
UML diagram file    
Program Features    

**The command-line menu allows the user to:**

Add a printed book  
Add an audiobook    
Display all books   
Search books    
Sort books  
Display statistics  
Display the last 6 books added overall  
Display the last 3 printed books added  
Display the last 3 audiobooks added 
Save books to a file    
Load books from a file  
Clear the current book list 
Exit the program    

After each menu option, the program asks the user if they want to continue by entering Y or N. I used this format because it is similar to the command-line menu examples from class.

### File Loading and Saving

The program can load book information from a text file. I used the sample text file that was provided with the project:

sample TXT Project file.txt

The program can also save the current list of books to another text file. For example, the user can save the books as:

saved_books.txt



**BookInterface**

BookInterface is the interface for the project. It contains the required methods for the book application.
The interface includes methods for displaying all books, counting the number of books in each genre, and calculating the total cost of all stored books.

**Book**

Book is an abstract class. I made it abstract because printed books and audiobooks share common information, but a general book does not know exactly how to calculate its cost.
The Book class stores the common information for all books: Title, Author, Genre, Book type.   

The Book class also has an abstract getCost method. This means every subclass of Book must provide its own cost formula.

**PrintedBook**

PrintedBook extends Book. It represents a printed book and stores the number of pages.
The cost of a printed book is calculated by multiplying the number of pages by 0.50.
PrintedBook also keeps track of the last 3 printed books added to the application. It also calculates the average number of pages for all printed books added.

**AudioBook**

AudioBook extends Book. It represents an audiobook and stores the duration in minutes.
The cost of an audiobook is calculated by multiplying the number of minutes by 0.25.
AudioBook also keeps track of the last 3 audiobooks added to the application. It also calculates the average duration for all audiobooks added.

**BookApplication**

BookApplication manages the main list of books. It stores the books in an ArrayList and handles most of the main program operations.

BookApplication handles:

Adding books    
Searching by title  
Searching by author 
Searching by genre  
Searching by book type  
Sorting books   
Displaying all books    
Displaying statistics   
Displaying the last 6 books added   
Displaying the last 3 printed books 
Displaying the last 3 audiobooks    
Saving books    
Loading books   
Clearing the book list      

**FileManager**

FileManager handles file input and output. It reads book data from a text file and writes book data to a text file.
I separated file handling into its own class so that BookApplication does not have to contain all of the file reading and writing code.

**Main**

Main contains the command-line interface. It displays the menu, gets user input, and calls the correct methods from BookApplication.
The menu uses numbered options and asks the user if they want to continue after each action.

**TestCase**

TestCase is a separate test driver file. I used it to quickly test the required project cases without having to manually type every book into the menu each time.
The main user program is Main.java. TestCase.java is just for testing and demonstration.

### OOP Concepts Used   

**Encapsulation**

Encapsulation is used by making the main fields private and using getter and setter methods.

For example, the title, author, genre, book type, pages, and duration are not directly changed from outside the class. Other classes use methods to access or update those values.

Inheritance

Inheritance is used because PrintedBook and AudioBook both extend the Book class.

This means PrintedBook and AudioBook reuse the common fields and methods from Book, while also adding their own specific fields and methods.

Polymorphism

Polymorphism is used because the program stores both printed books and audiobooks in one ArrayList of Book objects.

When the program calls getCost, Java runs the correct version of the method depending on whether the object is a PrintedBook or an AudioBook.

Abstraction

Abstraction is used with the abstract Book class and the abstract getCost method.

The Book class says that every book must have a cost, but it does not provide one general formula. The subclasses decide how cost is calculated.

Test Cases Demonstrated

The project demonstrates the required test cases from the project instructions.

**The test cases include:**

Loading the provided sample text file   
Adding more than 8 books    
Displaying the last 6 books added overall   
Displaying the last 3 printed books added   
Displaying the last 3 audiobooks added  
Testing a printed book with 0 pages 
Testing an audiobook with 0 minutes 
Verifying that 0 pages gives a cost of 0.00 
Verifying that 0 minutes gives a cost of 0.00   
Testing a 1000 page printed book    
Testing a 2000 minute audiobook 
Verifying the cost calculations for large values    
Counting books by genre 
Searching by genre  
Searching by author 
Saving books to a file  
Clearing the book list  
Loading books back from a saved file    
Verifying that the loaded data is still correct 

### Notes

I made the project a command-line application because that matches the style of several examples from class. I used a simple numbered menu and a Y/N continue question after each action. I also added comments in the code to explain the main sections and where the object-oriented programming concepts are being used.