package biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


import static org.mockito.Mockito.*;

public class BibliotecaTest {
    private String[] books;
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
        verify(printStream, times(1)).println("The chosen option was 1");
        assertThat(option, is("1"));
    }


}
