package biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaTest {
    private Book[] books = {
            new Book("Harry Potter, vol. 1", "J. K. Rowling", 2007),
            new Book("Becoming", "Michelle Obama", 2019),
            new Book("An American Marriage", "Tayari Jones", 2018)
    };
    Movie[] movies = {
            new Movie("Parasite", "Bong Joon Ho", 2019, 8.6),
            new Movie("Little Women", "Greta Gerwig", 2019, 7.9),
            new Movie("Rocket Man", "Dexter Fletcher", 2019, 7.3)
    };
    User[] users = {
            new User("111-1111", "123456", "Maria Santos", "maria@santos.com", "98888-8888", "librarian"),
            new User("2222-1111", "123456", "Jose Santos", "jose@santos.com", "98888-8887", "customer"),
    };
    private PrintStream printStream;
    private Biblioteca biblioteca;
    private BufferedReader bufferedReader;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        biblioteca = new Biblioteca(printStream, bufferedReader, books, movies);
        biblioteca.setUsers(users);
    }

    @Test
    public void shouldSayHello() {
        biblioteca.sayHello();

        verify(printStream, times(1)).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void shouldShowMenuOption() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");

        String option = biblioteca.chooseMenuOption();

        assertThat(option, is("1"));
    }

    @Test
    public void shouldNotifyAnInvalidChoice() throws IOException {
        when(bufferedReader.readLine()).thenReturn("c").thenReturn("1");
        biblioteca.chooseMenuOption();

        verify(printStream, times(1)).println("Invalid Option. Choose a valid one.");
    }

    @Test
    public void shouldDisplayAListOfBooks() {
        biblioteca.printListOfBooks();
        verify(printStream, times(1)).printf(anyString(), anyString(), anyString(), anyString());
        verify(printStream, times(3)).printf(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void shouldCheckOutABook() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");

        biblioteca.checkoutBook();

        assertThat(books[1].checked, is(true));
        verify(printStream, times(1)).println("Thank you! Enjoy the book.");

        books[1].checked = false;
    }

    @Test
    public void shouldNotPrintACheckedOutBook() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");
        biblioteca.checkoutBook();

        assertThat(books[1].checked, is(true));

        biblioteca.printListOfBooks();

        verify(printStream, times(2)).printf(anyString(), anyString(), anyString(), anyString());
        verify(printStream, times(5)).printf(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void shouldNotifyUserIfTheyTryToCheckOutAnUnavailableBook() throws IOException {
        books[1].checked = true;

        when(bufferedReader.readLine()).thenReturn("6");

        biblioteca.checkoutBook();
        verify(printStream, times(1)).println("Sorry, that book is not available.");
    }

    @Test
    public void shouldReturnABook() throws IOException {
        books[1].checked = true;
        when(bufferedReader.readLine()).thenReturn("2");

        biblioteca.returnBook();

        assertThat(books[1].checked, is(false));
        verify(printStream, times(1)).println("Thank you for returning a book.");
        books[1].checked = false;
    }

    @Test
    public void shouldNotifyUserIfTheyTryToReturnAnInvalidBook() throws IOException {
        when(bufferedReader.readLine()).thenReturn("7");

        biblioteca.returnBook();
        verify(printStream, times(1)).println("That is not a valid book to return.");
    }

    @Test
    public void shouldDisplayAListOfMovies() {
        biblioteca.printListOfMovies();
        verify(printStream, times(1)).printf(anyString(), anyString(), anyString(), anyString(), anyString());
        verify(printStream, times(3)).printf(anyString(), anyInt(), anyString(), anyString(), anyInt(), anyDouble());
    }

    @Test
    public void shouldCheckoutAMovie() throws IOException, InvalidMovieException {
        when(bufferedReader.readLine()).thenReturn("2");

        biblioteca.checkoutMovie();

        assertThat(movies[1].checked, is(true));
        verify(printStream, times(1)).println("Thank you! Enjoy the movie.");

        movies[1].checked = false;
    }

    @Test
    public void shouldLogin() throws IOException, InvalidUserException {
        when(bufferedReader.readLine()).thenReturn("111-1111").thenReturn("123456");

        biblioteca.login();

        assertThat(users[0].isLogged(), is(true));
    }

    @Test(expected = InvalidUserException.class)
    public void shouldNotLogin() throws IOException, InvalidUserException {
        when(bufferedReader.readLine()).thenReturn("111-1161").thenReturn("123456");
        biblioteca.login();
    }

    @Test
    public void shouldShowUnavailableBooks() throws IOException, InvalidUserException {
        when(bufferedReader.readLine()).thenReturn("111-1111").thenReturn("123456");

        biblioteca.login();

        books[0].checkout(users[0]);

        biblioteca.showUnavailableBooks();

        verify(printStream, times(1)).printf(anyString(), anyInt(), anyString(), anyString());
    }

    @Test
    public void shouldPrintUserInformation() throws IOException, InvalidUserException {
        when(bufferedReader.readLine()).thenReturn("111-1111").thenReturn("123456");

        biblioteca.login();

        biblioteca.showUserInformation();

        verify(printStream, times(1)).println("Name: " + users[0].getName());
    }


}
