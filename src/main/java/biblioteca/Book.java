package biblioteca;

public class Book {
    String title;
    String author;
    int year;
    boolean checked = false;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
