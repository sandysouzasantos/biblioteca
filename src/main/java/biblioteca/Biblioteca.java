package biblioteca;

import java.io.BufferedReader;
import java.io.PrintStream;

public class Biblioteca {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private String[] books;

    public Biblioteca(PrintStream printStream, BufferedReader bufferedReader, String[] books) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.books = books;
    }

    public void sayHello(){
        printStream.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }


}
