package biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {
        Book[] books = {
                new Book("Harry Potter, vol. 1", "J. K. Rowling", 2007),
                new Book("Becoming", "Michelle Obama", 2019),
                new Book("An American Marriage", "Tayari Jones", 2018)
        };
        Biblioteca biblioteca = new Biblioteca(System.out, new BufferedReader(new InputStreamReader(System.in)), books);
        biblioteca.sayHello();
        while (biblioteca.chooseMenuOption() != "") {};
    }
}
