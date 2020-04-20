package biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Biblioteca {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Book[] books;
    private Movie[] movies;

    public Biblioteca(PrintStream printStream, BufferedReader bufferedReader, Book[] books, Movie[] movies) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.books = books;
        this.movies = movies;
    }

    public void sayHello() {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public String chooseMenuOption() {
        printStream.println("\nSelect an option:\n1. List of books\n2. Checkout a book\n3. Return a book\n4. Quit");
        String option;

        try {
            option = bufferedReader.readLine();

            switch (option) {
                case "1":
                    printListOfBooks();
                    break;
                case "2":
                    checkOutBook();
                    break;
                case "3":
                    returnBook();
                    break;
                case "4":
                    return "";
                default:
                    printStream.println("Invalid Option. Choose a valid one.");
                    return chooseMenuOption();
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
        printStream.println("Choose a book (1, 2, ...):");

        Book book;
        try {
            book = getBook();
        } catch (InvalidBookException e) {
            printStream.println("Sorry, that book is not available.");
            return;
        }

        if (!book.checked) {
            book.setChecked();
            printStream.println("Thank you! Enjoy the book.");
        } else {
            printStream.println("Sorry, that book is not available.");
        }
    }


    public void returnBook() {
        printStream.println("Which book would you like to return?");

        Book book;
        try {
            book = getBook();
        } catch (InvalidBookException e) {
            printStream.println("That is not a valid book to return.");
            return;
        }

        if (book.checked) {
            book.setUnchecked();
            printStream.println("Thank you for returning a book.");
        } else {
            printStream.println("That is not a valid book to return.");
        }
    }

    private Book getBook() throws InvalidBookException {
        String option;
        Book book;

        try {
            option = bufferedReader.readLine();
            book = books[Integer.parseInt(option) - 1];
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidBookException();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidBookException();
        }

        return book;
    }

    public void printListOfMovies() {
        printStream.printf("%-35s%-16s%-8s%8s%n", "Name:", "Director:", "Year:", "Rating");

        int index = 1;
        for (Movie movie : movies) {
//            if (!movie.checked) {
                printStream.printf("%-3s%-32s%-16s%-8s%8s%n", index, movie.name, movie.director, movie.year, movie.rating);
//            }
            index++;
        }
    }

}
