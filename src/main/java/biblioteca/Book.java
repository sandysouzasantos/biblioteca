package biblioteca;

public class Book {
    String title;
    String author;
    int year;
    private User renter;
    boolean checked = false;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void setChecked() {
        this.checked = true;
    }

    public void setUnchecked() {
        this.checked = false;
    }

    public void checkout(User user) {
        this.setChecked();
        this.renter = user;
    }

    public User getRenter() {
        return renter;
    }
}
