package biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Biblioteca {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Book[] books;
    private Movie[] movies;
    private User[] users;
    private User loggedUser;

    public Biblioteca(PrintStream printStream, BufferedReader bufferedReader, Book[] books, Movie[] movies) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.books = books;
        this.movies = movies;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void sayHello() {
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public String chooseMenuOption() {
        printStream.println("\nSelect an option:");
        printStream.println("0. Log in");
        printStream.println("1. List of books");
        printStream.println("2. List of movies");
        if (loggedUser != null) {
            printStream.println("3. Checkout a book");
            printStream.println("4. Return a book");
            printStream.println("5. Checkout a movie");
            printStream.println("6. Return a movie");
            if (loggedUser.getRole().equals("librarian")) {
                printStream.println("7. Show list of checked out books");
            }
        }
        printStream.println("Q. Return a movie");
        String option;

        try {
            option = bufferedReader.readLine();

            switch (option) {
                case "0":
                    try {
                        login();
                    } catch (InvalidUserException e) {
                        printStream.println("Invalid Library Number or password");
                        return chooseMenuOption();
                    }
                    break;
                case "1":
                    printListOfBooks();
                    break;
                case "2":
                    printListOfMovies();
                    break;
                case "3":
                    if (loggedUser != null)
                        checkoutBook();
                    break;
                case "4":
                    if (loggedUser != null)
                        returnBook();
                    break;
                case "5":
                    if (loggedUser != null)
                        checkoutMovie();
                    break;
                case "6":
                    if (loggedUser != null)
                        returnMovie();
                    break;
                case "7":
                    if (loggedUser != null && loggedUser.getRole().equals("librarian"))
                        showUnavailableBooks();
                    break;
                case "Q":
                case "q":
                    return "";
                default:
                    printStream.println("Invalid Option. Choose a valid one.");
                    return chooseMenuOption();
            }

        } catch (IOException | InvalidMovieException e) {
            e.printStackTrace();
            return "";
        }

        return option;
    }

    public void printListOfBooks() {
        printStream.printf("%-35s%-16s%8s%n", "Title:", "Author:", "Year:");

        int index = 1;
        for (Book book : books) {
            if (!book.getChecked()) {
                printStream.printf("%-3s%-32s%-16s%8s%n", index, book.getTitle(), book.getAuthor(), book.getYear());
            }
            index++;
        }

    }

    public void checkoutBook() {
        printListOfBooks();
        printStream.println("Choose a book (1, 2, ...):");

        Book book;
        try {
            book = getBook();
        } catch (InvalidBookException e) {
            printStream.println("Sorry, that book is not available.");
            return;
        }

        if (!book.getChecked()) {
            book.checkout(loggedUser);
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

        if (book.getChecked()) {
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
            if (!movie.getChecked()) {
                printStream.printf("%-3s%-32s%-16s%-8s%8s%n", index, movie.getName(), movie.getDirector(), movie.getYear(), movie.getRating());
            }
            index++;
        }
    }

    public void checkoutMovie() throws InvalidMovieException {
        printListOfMovies();
        printStream.println("Choose a movie (1, 2, ...):");

        Movie movie;
        try {
            movie = getMovie();
        } catch (InvalidMovieException e) {
            printStream.println("Sorry, that movie is not available.");
            return;
        }

        if (!movie.getChecked()) {
            movie.checkout(loggedUser);
            printStream.println("Thank you! Enjoy the movie.");
        } else {
            printStream.println("Sorry, that movie is not available.");
        }
    }

    private Movie getMovie() throws InvalidMovieException {
        String option;
        Movie movie;

        try {
            option = bufferedReader.readLine();
            movie = movies[Integer.parseInt(option) - 1];
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidMovieException();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidMovieException();
        }

        return movie;
    }

    public void login() throws IOException, InvalidUserException {
        printStream.println("Library Number: ");
        String libraryNumber = bufferedReader.readLine();

        printStream.println("Password: ");
        String password = bufferedReader.readLine();

        for (User user : users) {
            if (user.getLibraryNumber().equals(libraryNumber)) {
                if (user.getPassword().equals(password)) {
                    user.loginUser();
                    loggedUser = user;
                    break;
                }
            }
        }

        if (loggedUser == null) {
            throw new InvalidUserException();
        }
    }

    public void showUnavailableBooks() {
        if (loggedUser != null && loggedUser.getRole() == "librarian") {

            int index = 1;
            for (Book book : books) {
                if (book.getChecked()) {
                    printStream.printf("%-3s%-32s%-16s%n", index, book.getTitle(), book.getRenter().getName());
                }
                index++;
            }
        }
    }

    public void showUserInformation() {
        if (loggedUser != null) {
            printStream.println("Name: " + loggedUser.getName());
            printStream.println("Email: " + loggedUser.getEmail());
            printStream.println("Phone: " + loggedUser.getPhone());
        }
    }

    public void returnMovie() {
        printStream.println("Which movie would you like to return?");

        Movie movie;
        try {
            movie = getMovie();
        } catch (InvalidMovieException e) {
            printStream.println("That is not a valid movie to return.");
            return;
        }

        if (movie.getChecked()) {
            movie.setUnchecked();
            printStream.println("Thank you for returning a movie.");
        } else {
            printStream.println("That is not a valid movie to return.");
        }
    }

}
