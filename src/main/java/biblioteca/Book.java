package biblioteca;

public class Book {
    private String title;
    private String author;
    private int year;
    private User renter;
    private boolean checked = false;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void setUnchecked() {
        this.checked = false;
        this.renter = null;
    }

    public void checkout(User user) {
        this.checked = true;
        this.renter = user;
    }

    public User getRenter() {
        return renter;
    }

    public boolean getChecked() {
        return checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }
}
