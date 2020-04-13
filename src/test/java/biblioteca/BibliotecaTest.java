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
    private PrintStream printStream;
    private Biblioteca biblioteca;
    private BufferedReader bufferedReader;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        biblioteca = new Biblioteca(printStream, bufferedReader, books);
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
        when(bufferedReader.readLine()).thenReturn("c");
        biblioteca.chooseMenuOption();

        verify(printStream, times(1)).println("Invalid Option. Choose a valid one.");
    }

    @Test
    public void shouldDisplayAListOfBooks(){
        biblioteca.printListOfBooks();
        verify(printStream, times(1)).printf(anyString(), anyString(), anyString(), anyString());
        verify(printStream, times(3)).printf(anyString(),anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void shouldCheckOutABook() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");

        biblioteca.checkOutBook();

        assertThat(books[1].checked, is(true));
        verify(printStream, times(1)).println("Thank you! Enjoy the book.");

        books[1].checked = false;
    }

    @Test
    public void shouldNotPrintACheckedOutBook() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");
        biblioteca.checkOutBook();

        assertThat(books[1].checked, is(true));

        biblioteca.printListOfBooks();

        verify(printStream, times(2)).printf(anyString(), anyString(), anyString(), anyString());
        verify(printStream, times(5)).printf(anyString(),anyInt(), anyString(), anyString(), anyInt());
    }



}
