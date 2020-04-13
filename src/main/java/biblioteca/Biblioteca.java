package biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Biblioteca {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Book[] books;

    public Biblioteca(PrintStream printStream, BufferedReader bufferedReader, Book[] books) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.books = books;
    }

    public void sayHello() {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public String chooseMenuOption() {
        printStream.println("Select an option:\n1. List of books\n");
        String option;

        try {
            option = bufferedReader.readLine();
            if (option.equals("1")) {
                checkOutBook();
            } else {
                printStream.println("Invalid Option. Choose a valid one.");
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return option;
    }

    public void printListOfBooks() {
        printStream.printf("%-35s%-16s%8s%n", "Title:", "Author:", "Year:");

        int index = 1;
        for (Book book : books) {
            if (!book.checked) {
                printStream.printf("%-3s%-32s%-16s%8s%n", index, book.title, book.author, book.year);
            }
            index++;
        }

    }

    public void checkOutBook() {
        printListOfBooks();
        printStream.println("Choose a book:");

        Book book = getBook();

        assert book != null;
        if (!book.checked) {
            book.checked = true;
            printStream.println("Thank you! Enjoy the book.");
        } else {
            printStream.println("Sorry, that book is not available.");
        }
    }

    public void returnBook(){
        printStream.println("Which book would you like to return?");

        Book book = getBook();

        assert book != null;
        if (book.checked) {
            book.checked = false;
            printStream.println("Thank you for returning a book.");
        } else {
            printStream.println("Sorry, that book is not available.");
        }
    }

    private Book getBook(){
        String option;
        Book book;

        try {
            option = bufferedReader.readLine();
            book = books[Integer.parseInt(option) - 1];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            printStream.println("Sorry, that book is not available.");
            return null;
        }

        return book;
    }

}
