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
                displayListOfBooks();
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

    public void displayListOfBooks() {
        printStream.printf("%-32s%-16s%8s%n", "Title:", "Author:", "Year:");

        for (Book book : books) {
            printStream.printf("%-32s%-16s%8s%n", book.title, book.author, book.year);
        }
    }


}
